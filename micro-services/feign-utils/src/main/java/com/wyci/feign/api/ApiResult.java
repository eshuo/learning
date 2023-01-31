package com.wyci.feign.api;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-01-30 15:49
 * @Version V1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一响应消息报文
 *
 * @author zhipeng.wang
 * @date 2021/9/2 - 10:33
 */
@Data
public class ApiResult<T> implements Serializable {

    @ApiModelProperty(value = "自定义响应编码", required = true)
    private int code = 0;

    @ApiModelProperty(value = "响应返回信息", required = true)
    private String message;

    @ApiModelProperty(value = "响应返回数据")
    private T data;

    @ApiModelProperty(value = "HTTP状态码")
    private int status;

    @ApiModelProperty(value = "响应时间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Date timestamp;

    private ApiResult() {
        this.timestamp = new Date();
    }

    /*private ApiResult(ApiResultCode apiResultCode, int httpStatus) {
        this(apiResultCode.getCode(), apiResultCode.getMessage(), null, httpStatus);
    }

    private ApiResult(ApiResultCode apiResultCode, String message, int httStatus) {
        this(apiResultCode.getCode(), message, null, httStatus);
    }

    private ApiResult(ApiResultCode apiResultCode, T data, int httStatus) {
        this(apiResultCode.getCode(), apiResultCode.getMessage(), data, httStatus);
    }*/

    private ApiResult(ApiResultCode apiResultCode, String message, T data, int httpStatus) {
        this(apiResultCode.getCode(), message, data, httpStatus);
    }

    private ApiResult(int code, String message, T data, int httpStatus) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = httpStatus;
        this.timestamp = new Date();
    }

    /**
     * 返回响应结果
     *
     * @return ApiResult<T>
     */
    public static <T> ApiResult<T> success(int code, String message, T data, int httpStatus) {
        return new ApiResult<>(code, message, data, httpStatus);
    }

    public static <T> ApiResult<T> success(int code, String message, T data) {
        return success(code, message, data, 200);
    }

    public static <T> ApiResult<T> success(int code, String message) {
        return success(code, message, null);
    }

    public static <T> ApiResult<T> success(String message, T data) {
        return success(ApiResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ApiResult<T> success(String message) {
        return success(message, null);
    }

    public static <T> ApiResult<T> success() {
        return success("成功");
    }

    public static <T> ApiResult<T> success(ApiResultCode resultCode, T data) {
        return success(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> ApiResult<T> data(T data) {
        return success(ApiResultCode.SUCCESS, data);
    }


    public static <T> ApiResult<T> fail(int code, String message, T data, int httpStatus) {
        return new ApiResult<>(code, message, data, httpStatus);
    }

    public static <T> ApiResult<T> fail(int code, String message, T data) {
        return fail(code, message, data, 500);
    }

    public static <T> ApiResult<T> fail(int code, String message) {
        return fail(code, message, null);
    }

    public static <T> ApiResult<T> fail(String message, T data) {
        return fail(ApiResultCode.ERROR.getCode(), message, data);
    }

    public static <T> ApiResult<T> fail(String message) {
        return fail(message, null);
    }

    public static <T> ApiResult<T> fail() {
        return fail("失败");
    }

    public static <T> ApiResult<T> fail(ApiResultCode resultCode, T data) {
        return fail(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> ApiResult<T> fail(ApiResultCode resultCode, int httpStatus) {
        return fail(resultCode.getCode(), resultCode.getMessage(), null, httpStatus);
    }

    public static <T> ApiResult<T> condition(boolean flag) {
        return flag ? success() : fail();
    }

    public static <T> ApiResult<T> empty(int code, String message, int httpStatus) {
        return new ApiResult<>(code, message, null, httpStatus);
    }

    public static <T> ApiResult<T> empty(int code, String message) {
        return empty(code, message, 500);
    }

    public static <T> ApiResult<T> empty(String message) {
        return empty(ApiResultCode.NO_CONTENT.getCode(), message);
    }

    public static <T> ApiResult<T> empty() {
        return empty("空值");
    }

    public static <T> ApiResult<T> empty(ApiResultCode resultCode) {
        return empty(resultCode.getCode(), resultCode.getMessage());
    }


}

