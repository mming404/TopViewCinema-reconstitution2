package com.ysm.www.auth.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ysm.www.auth.constant.SecurityConstant;
import com.ysm.www.auth.dto.SignResult;
import com.ysm.www.auth.properties.SecurityProperties;
import com.ysm.www.util.IDataStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Albumen
 * @date 2020/3/2
 */
@Slf4j
@Component
@AllArgsConstructor
public class SecurityUtil {
    public JwtUtil jwtUtil;
    private IDataStore dataStore;
    private SecurityProperties securityProperties;

    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (Objects.nonNull(principal) && principal instanceof String) {
                return Long.valueOf((String) principal);
            }
        }
        throw new AuthenticationCredentialsNotFoundException("登陆信息不存在！");
    }

    public static <T> T getUserDetail(Class<T> tClass) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof String) {
            return JSONObject.parseObject((String) details, tClass);
        } else {
            return null;
        }
    }

    public SignResult login(List<String> userId, List<String> permissionList, Object userDetail) {
        String userIdString = Base64.getEncoder().encodeToString(JSONObject.toJSONString(userId).getBytes());
        return login(userIdString, permissionList, userDetail);
    }

    public SignResult login(String userId, List<String> permissionList, Object userDetail) {
        String uuid = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        String token = jwtUtil.create(uuid, securityProperties.getExpire());

        //将uuid加上安全前缀作为key userid作为值 存入redis  过期时间统一为2h
        boolean result = dataStore.put(SecurityConstant.TOKEN_PREFIX + uuid,
                userId,
                securityProperties.getExpire());

        result = result && dataStore.put(SecurityConstant.PERMISSION_PREFIX + userId,
                JSONArray.toJSONString(permissionList),
                securityProperties.getExpire());

        result = result && dataStore.put(SecurityConstant.INFORMATION_PREFIX + userId,
                JSONObject.toJSONString(userDetail),
                securityProperties.getExpire());

        token = SecurityConstant.STATE_PREFIX + token;

        SignResult signResult = new SignResult();
        if (result) {
            signResult.setSuccess(true);
            signResult.setToken(token);
            signResult.setExpireAt(System.currentTimeMillis() + securityProperties.getExpire());

            // Session 记录
            if (securityProperties.isEnableSession()) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (Objects.nonNull(requestAttributes)) {
                    HttpServletRequest request = requestAttributes.getRequest();
                    request.getSession().setAttribute(SecurityConstant.AUTHORIZATION_HEADER, token);
                } else {
                    throw new IllegalStateException("获取requestAttributes出错");
                }
            }
        } else {
            signResult.setSuccess(false);
        }
        return signResult;
    }
}
