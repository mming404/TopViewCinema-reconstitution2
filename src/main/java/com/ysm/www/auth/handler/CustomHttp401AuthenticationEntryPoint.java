package com.ysm.www.auth.handler;

import com.ysm.www.entity.result.CommonResult;
import com.ysm.www.entity.result.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.ysm.www.auth.constant.SecurityConstant.ACCESS_DENY;

/**
 * @Description: TODO  用户登录失败处理器
 * @Author MiSinG
 * @Date 2023/7/16
 * @Version V1.0
 **/
@Component
public class CustomHttp401AuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultUtil.printCode(httpServletResponse,ACCESS_DENY, 401);
    }
}
