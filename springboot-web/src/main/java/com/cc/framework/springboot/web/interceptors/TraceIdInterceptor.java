package com.cc.framework.springboot.web.interceptors;

import com.cc.framework.common.Constants;
import com.cc.framework.common.utils.TraceUtil;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截所有请求添加traceId
 * @author sen.hu
 * @date 2018/11/27 17:53
 **/
public class TraceIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(Constants.TRACE_ID_LOGWEB_HEADER);
        if (StringUtils.isEmpty(traceId)) {
            traceId = TraceUtil.genTraceId();
        }
        MDC.put(Constants.TRACE_ID_LOGFILE_NAME, traceId);
        return true;
    }
}
