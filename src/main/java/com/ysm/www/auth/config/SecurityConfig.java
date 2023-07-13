package com.ysm.www.auth.config;


import com.ysm.www.auth.handler.CustomAccessDeniedHandler;
import com.ysm.www.auth.handler.CustomAuthenticationFilter;
import com.ysm.www.auth.handler.CustomHttp401AuthenticationEntryPoint;
import com.ysm.www.auth.properties.SecurityProperties;
import com.ysm.www.auth.util.JwtUtil;
import com.ysm.www.util.IDataStore;
import lombok.AllArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * 配置Spring Security
 *
 * @author Albumen
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomHttp401AuthenticationEntryPoint customHttp401AuthenticationEntryPoint;
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    private JwtUtil jwtUtil;
    private IDataStore dataStore;
    private IPermissionPath permissionPath;
    private SecurityProperties securityProperties;

    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        permissionPath.ignorePath().forEach(ignoring::antMatchers);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  //前后端分离 关闭此功能
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)  //不从session中获取
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager(), dataStore, jwtUtil, securityProperties))
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customHttp401AuthenticationEntryPoint);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http.authorizeRequests();
        permissionPath.permitPath().forEach(path -> authorizeRequests.antMatchers(path).permitAll());
        permissionPath.authenticatedPath().forEach(path -> authorizeRequests.antMatchers(path).authenticated());
    }
}
