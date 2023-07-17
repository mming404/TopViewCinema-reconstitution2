package com.ysm.www.auth.config;

import com.ysm.www.auth.handler.CustomAccessDeniedHandler;
import com.ysm.www.auth.handler.CustomAuthenticationFilter;
import com.ysm.www.auth.handler.CustomHttp401AuthenticationEntryPoint;
import com.ysm.www.auth.handler.IPAccessLimiterFilter;
import com.ysm.www.auth.property.SecurityProperty;
import com.ysm.www.auth.util.JwtUtil;
import com.ysm.www.util.IDataStore;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/16
 * @Version V1.0
 **/
@AllArgsConstructor
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private IDataStore dataStore;

    @Resource
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Resource
    private CustomHttp401AuthenticationEntryPoint customHttp401AuthenticationEntryPoint;


    @Resource
    private SecurityProperty securityProperty;


    @Resource
    private IPAccessLimiterFilter ipAccessLimiterFilter;



    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        Arrays.stream(securityProperty.getIgnore()).forEach(ignoring::antMatchers);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //前后端分离 关闭此功能
                .csrf().disable()

                //禁用session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)

                //拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(securityProperty.getAllowed()).permitAll()
                .antMatchers(securityProperty.getNeed()).authenticated()

                //添加异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customHttp401AuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)

                //添加自定义过滤器
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager(),dataStore,jwtUtil))
                .addFilterBefore(ipAccessLimiterFilter, CustomAuthenticationFilter.class);

    }
}
