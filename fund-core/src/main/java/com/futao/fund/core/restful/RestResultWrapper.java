package com.futao.fund.core.restful;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.futao.fund.core.util.HttpServletUtils;
import com.futao.fund.core.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ft <futaosmile@gmail.com>
 * @date 2022/5/22
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class RestResultWrapper implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        JSONObject userObject = UserUtils.currentUser();
        if (userObject != null) {
            String name = userObject.getString("name");
            JSONObject department = userObject.getJSONObject("department");
            String departmentName = StringUtils.EMPTY;
            if (department != null) {
                departmentName = department.getString("name");
            }
            log.info("当前用户:{}，dep:{}，requestUri:{}", name, departmentName, HttpServletUtils.getRequest().getRequestURI());
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof RestResult) {
            return o;
        }
        if (o instanceof String || aClass.isAssignableFrom(StringHttpMessageConverter.class)) {
            return JSON.toJSONString(RestResult.simpleSuccess(o), SerializerFeature.WriteNullListAsEmpty);
        }
        return RestResult.simpleSuccess(o);
    }
}
