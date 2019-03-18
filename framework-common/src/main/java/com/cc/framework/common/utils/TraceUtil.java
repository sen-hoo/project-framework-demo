package com.cc.framework.common.utils;

/**
 *
 * @author sen.hu
 * @date 2019/3/18 15:47
 **/
public class TraceUtil {


    public static String genTraceId() {
        return java.util.UUID.randomUUID().toString();
    }

}
