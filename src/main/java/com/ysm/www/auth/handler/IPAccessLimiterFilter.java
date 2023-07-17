package com.ysm.www.auth.handler;

import com.ysm.www.auth.limiter.IPAccessLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/17
 * @Version V1.0
 **/
@Component
@Slf4j
public class IPAccessLimiterFilter implements Filter {


    private IPAccessLimiter ipAccessLimiter;

    public IPAccessLimiterFilter(IPAccessLimiter ipAccessLimiter) {
        this.ipAccessLimiter = ipAccessLimiter;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String ip = getIp((HttpServletRequest) request);
        log.debug("ip:{} 在 {} 时间访问了",ip, LocalDateTime.now());
        Assert.isTrue(ipAccessLimiter.ifAllowed(ip),"该ip访问次数过多，疑似恶意攻击");
        filterChain.doFilter(request,response);
    }


    public String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress.split(",")[0];
    }

}
