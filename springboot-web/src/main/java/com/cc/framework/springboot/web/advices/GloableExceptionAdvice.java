package com.cc.framework.springboot.web.advices;

import com.cc.framework.common.model.ResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Description web全局异常处理
 * @Author sen.hu
 * @Date 2018/11/29 13:36
 **/
@RestControllerAdvice
public class GloableExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(GloableExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBuilder.Result exception500(Exception e) {
        logger.error("ServerError:", e);
        return ResultBuilder.getResultException(ResultBuilder.ResultException.ServerError);
    }
    @ExceptionHandler(value = {MissingServletRequestParameterException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultBuilder.Result exception400(MissingServletRequestParameterException e) {
        return ResultBuilder.getResultException(ResultBuilder.ResultException.BadRequest);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBuilder.Result exception404(NoHandlerFoundException e) {
        return ResultBuilder.getResultException(ResultBuilder.ResultException.NotFound);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBuilder.Result exception405(MethodArgumentTypeMismatchException e) {
        return ResultBuilder.getResultException(ResultBuilder.ResultException.MethodNotAllowed);
    }
//    @ExceptionHandler(ResultBuilder.BusinessException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public ResultBuilder.Result businessException(ResultBuilder.CommonResultException e) {
//        return ResultBuilder.getBaseBusinessException(e.getMsg());
//    }

}
