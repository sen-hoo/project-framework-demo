package com.cc.framework.springboot.web.advices;

import com.cc.framework.common.Constants;
import com.cc.framework.common.model.ResultBuilder;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Date;

/**
 * @Description 拦截controller设置返回traceId 和 time
 * @Author sen.hu
 * @Date 2018/11/29 18:33
 **/
@RestControllerAdvice(basePackages = {"com.cc.framework.springboot.web.advices", "com.cc.framework.springboot.web.controllers"})
public class TraceIdResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body != null && body instanceof ResultBuilder.Result) {
            ResultBuilder.Result result = (ResultBuilder.Result) body;
            result.setTraceId(MDC.get(Constants.TRACE_ID_LOGFILE_NAME));
            result.setTime(new Date());
            return result;
        }
        return body;
    }
}
