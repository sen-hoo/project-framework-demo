package com.cc.framework.common.model;

import java.util.Date;

/**
 * 调用返回结果构建器
 * @author sen.hu
 * @date 2019/3/18 16:42
 **/
public class ResultBuilder {

    public static class Result<T> {

        private int code;
        private String msg;
        private T data;
        private String traceId;
        private Date time;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
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


    public static Result getSuccess() {
        Result result = new Result();
        return result;
    }
}
