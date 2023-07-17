package com.ysm.www.auth.handler;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.ysm.www.auth.constant.SecurityConstant;
import com.ysm.www.auth.entity.GrantedAuthorityImpl;
import com.ysm.www.auth.util.JwtUtil;
import com.ysm.www.util.IDataStore;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.ysm.www.auth.constant.SecurityConstant.*;

/**
 * @Description: TODO 自定义权限过滤器
 * @Author MiSinG
 * @Date 2023/7/16
 * @Version V1.0
 **/

public class CustomAuthenticationFilter extends BasicAuthenticationFilter {

    private IDataStore dataStore;

    private JwtUtil jwtUtil;


    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, IDataStore dataStore, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.dataStore = dataStore;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(AUTHORIZATION_HEADER);
        System.out.println(request.getRemoteAddr());
        // 判断是否使用Session获取Token
        if (StringUtils.isEmpty(jwt) && false) {
            jwt = (String) request.getSession().getAttribute(AUTHORIZATION_HEADER);
        }


        if (StringUtils.isNotBlank(jwt) && jwt.startsWith(STATE_PREFIX)) {
            jwt = jwt.substring(STATE_PREFIX.length());
            String uuid = jwtUtil.getSubject(jwt);
            String userid = dataStore.get(TOKEN_PREFIX + uuid);
            if (StringUtils.isNotEmpty(userid)) {

                //从redis取出数据
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                String permissionStr = dataStore.get(PERMISSION_PREFIX + userid);
                String userDate = dataStore.get(INFORMATION_PREFIX + userid);
                List<String> permissions = JSONArray.parseArray(permissionStr, String.class);
                if (Objects.nonNull(permissions) && permissions.size() > 0) {
                    for (String permission : permissions) {
                        authorities.add(new GrantedAuthorityImpl(permission));
                    }
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userid, null, authorities);
                authentication.setDetails(userDate);

                //向security上下文存入权限列表
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);
                return;
            }
        }


        //验证失败  给用户添加匿名信息  存入上下文
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthorityImpl("ROLE_ANONYMOUS"));
        AnonymousAuthenticationToken anonymousAuthenticationToken = new AnonymousAuthenticationToken("Anonymous", "Anonymous", authorities);
        SecurityContextHolder.getContext().setAuthentication(anonymousAuthenticationToken);
        chain.doFilter(request, response);


    }
}
