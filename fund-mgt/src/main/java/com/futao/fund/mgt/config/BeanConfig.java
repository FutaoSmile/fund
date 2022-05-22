package com.futao.fund.mgt.config;

import com.futao.fund.core.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/22
 */
@Configuration
public class BeanConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        corsFilterFilterRegistrationBean.setFilter(new CorsFilter());
        HashSet<String> urlPatterns = new HashSet<>();
        urlPatterns.add("/*");
        corsFilterFilterRegistrationBean.setUrlPatterns(urlPatterns);
        return corsFilterFilterRegistrationBean;
    }
}
