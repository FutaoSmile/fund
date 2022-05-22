package com.futao.fund.core.filters;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/22
 */
@Slf4j
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("跨域filter初始化,{}", JSON.toJSONString(filterConfig));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400");
            filterChain.doFilter(servletRequest, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        log.info("跨域filter销毁");
    }
}
