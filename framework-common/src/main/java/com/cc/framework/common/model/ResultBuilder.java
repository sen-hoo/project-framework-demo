package com.cc.framework.common.model;

import java.util.Date;

/**
 * 调用返回结果构建器
 * @author sen.hu
 * @date 2019/3/18 16:42
 **/
public class ResultBuilder {
    /**
     * 自定义返回异常
     */
    public interface CustomizedResultException {
        int getResultCode();
        String getResultMsg();
    }

    /**
     * 业务异常
     */
    public static class BusinessException extends RuntimeException {
        private final String messagesKey;
        private Object[] args;
        private String defaultMessage;

        public BusinessException(String messagesKey, Object[] args) {
            super(messagesKey);
            this.messagesKey = messagesKey;
            this.args = args;
        }

        public BusinessException(String messagesKey, Object[] args, String defaultMessage) {
            super(messagesKey);
            this.messagesKey = messagesKey;
            this.args = args;
            this.defaultMessage = defaultMessage;
        }

        public String getMessagesKey() {
            return messagesKey;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }

        public void setDefaultMessage(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }
    }

    /**
     * 返回枚举
     */
    public enum ResultException {
        Success(0, "Success"),
        ServerError(10500, "Server Error"),
        BadRequest(10400, "Bad Request"),
        MethodNotAllowed(10405, "Method Not Allowed"),
        NotFound(10404, "Uri Not Find"),
        CommonException(10900, "业务异常")

        ;

        private final int errorCode;
        private final String msg;

        ResultException(int errorCode, String msg) {
            this.errorCode = errorCode;
            this.msg = msg;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 调用返回
     * @param <T>
     */
    public static class Result<T> {

        private final int code;
        private final String msg;
        private T data;
        private String traceId;
        private Date time;

        private Result(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        private Result(int code, String msg, T data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }
    }

    /**
     * 获取成功的返回
     * @return
     */
    public static Result getSuccess() {
        return new Result(ResultException.Success.errorCode, ResultException.Success.msg);
    }

    /**
     * 获取成功数据返回
     * @param data  数据
     * @param <T>   返回数据类型
     * @return
     */
    public static <T> Result<T> getSuccess(T data) {
        return new Result(ResultException.Success.errorCode, ResultException.Success.msg, data);
    }

    /**
     * 获取异常返回
     * @param resultException
     * @return
     */
    public static Result getResultException(ResultException resultException) {
        return new Result(resultException.errorCode, resultException.msg);
    }

    /**
     * 生成自定义返回结果
     * @param customizedResultException
     * @return
     */
    public static Result getResultException(CustomizedResultException customizedResultException) {
        return new Result(customizedResultException.getResultCode(), customizedResultException.getResultMsg());
    }

    /**
     * 获取普通异常返回
     * @param msg   异常信息
     * @return
     */
    public static Result getResultException(String msg) {
        return new Result(ResultException.CommonException.errorCode, msg);
    }
}
