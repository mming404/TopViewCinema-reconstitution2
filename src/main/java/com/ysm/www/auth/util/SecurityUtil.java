package com.ysm.www.auth.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ysm.www.entity.po.User;
import com.ysm.www.util.IDataStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.ysm.www.auth.constant.SecurityConstant.*;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/16
 * @Version V1.0
 **/
@Component
@AllArgsConstructor
@Slf4j
public class SecurityUtil {


    private IDataStore dataStore;

    private JwtUtil jwtUtil;

    public static Integer getUserId() {
        //从上下文中获取 userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (Objects.nonNull(principal)) {
                return Integer.valueOf((String) principal);
            }
        }
        throw new AuthenticationCredentialsNotFoundException("登录信息不存在！");
    }


    /**
     * service 登录方法
     * @param userId
     * @param permissions
     * @return  有前缀的token
     */
    public String login(Integer userId, List<String> permissions, User userDate) {
        String uuid = UUID.randomUUID().toString();

        String jwt = jwtUtil.create(uuid, TOKEN_2H_TIME);
        boolean result = dataStore.put(TOKEN_PREFIX + uuid, userId.toString(), TOKEN_2H_TIME);

        result = result && dataStore.put(PERMISSION_PREFIX + userId, JSONArray.toJSONString(permissions), TOKEN_2H_TIME);

        result = result && dataStore.put(INFORMATION_PREFIX + userId, JSON.toJSONString(userDate), TOKEN_2H_TIME);

        //这个key当token过期时用来刷新token，时效:7days    续约token
        result = result && dataStore.put(TOKEN_REFRESH_PREFIX + jwt, userId.toString(), TOKEN_7D_TIME);

        return result ? STATE_PREFIX + jwt : null;
    }


    /**
     * 刷新token   7天内都可以刷新，过后必须登录
     *
     * @param oldToken 没有前缀的token
     * @return newToken 没有前缀
     */
    public String refreshToken(String oldToken) {

        if (StringUtils.isBlank(oldToken)) {
            return null;
        }
        //token可以解析表明未过期
        String subject = jwtUtil.getSubject(oldToken);
        if (subject != null) {
            return null;
        }
        //refreshToken已经不存在redis中，即已经被刷新过一次
        String userId = dataStore.get(TOKEN_REFRESH_PREFIX + oldToken);
        if (StringUtils.isEmpty(userId)) {
            return null;
        }

        //创建新token

        String uuid = UUID.randomUUID().toString();
        String newToken = jwtUtil.create(uuid, TOKEN_2H_TIME);
        //获得refreshToken剩余时间
        Long expireTime = dataStore.getExpireTime(TOKEN_REFRESH_PREFIX + oldToken);


        boolean result = dataStore.put(TOKEN_PREFIX + uuid, userId, TOKEN_2H_TIME);
        //创建新的refreshToken
        result = result && dataStore.put(TOKEN_REFRESH_PREFIX + newToken, userId, expireTime);
        //删除旧的refreshToken，防止有人用旧的(已过期)的token再次刷新
        result = result && dataStore.remove(TOKEN_REFRESH_PREFIX + oldToken);

        return result ? newToken : null;
    }
}
