package com.cc.framework.common.model;

import java.util.Date;

/**
 * 调用返回结果构建器
 * @author sen.hu
 * @date 2019/3/18 16:42
 **/
public class ResultBuilder {

    public interface CustomizedException {
        int getResultCode();
        String getResultMsg();
    }


    public enum ResultException {
        Success(0, "Success"),
        ServerError(10500, "Server Error"),
        BadRequest(10400, "Bad Request"),
        MethodNotAllowed(10405, "Method Not Allowed"),
        NotFound(10404, "Uri Not Find")
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

    public static Result getResultException(ResultException resultException) {
        return new Result(resultException.errorCode, resultException.msg);
    }
}
