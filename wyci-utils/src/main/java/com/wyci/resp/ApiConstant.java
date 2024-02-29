package com.wyci.resp;

import org.apache.http.HttpStatus;

/**
 */
public class ApiConstant {

    public static final int DEFAULT_HTTP_STATUS_OK = HttpStatus.SC_OK;
    public static final int DEFAULT_HTTP_STATUS_FAIL = HttpStatus.SC_INTERNAL_SERVER_ERROR;
    public static final int DEFAULT_HTTP_STATUS_NO_CONTENT = HttpStatus.SC_NO_CONTENT;

    /**
     * 默认为空消息
     */
    public static final String DEFAULT_NO_CONTENT_MESSAGE = "承载数据为空";
    /**
     * 默认成功消息
     */
    public final static String DEFAULT_SUCCESS_MESSAGE = "处理成功";
    /**
     * 默认失败消息
     */
    public final static String DEFAULT_FAIL_MESSAGE = "处理失败";

}
