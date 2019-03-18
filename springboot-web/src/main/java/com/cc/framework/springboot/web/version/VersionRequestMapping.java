package com.cc.framework.springboot.web.version;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自定义版本url请求
 * @author sen.hu
 * @date 2018/11/29 18:13
 **/
public class VersionRequestMapping extends RequestMappingHandlerMapping {

    @Nullable
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        RequestVersion requestVersion = handlerType.getAnnotation(RequestVersion.class);
        return requestVersion == null ? null : new RequestVersionCondition(requestVersion.vNo());
    }

    @Nullable
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        RequestVersion requestVersion = method.getDeclaredAnnotation(RequestVersion.class);
        return requestVersion == null ? null : new RequestVersionCondition(requestVersion.vNo());
    }
}
