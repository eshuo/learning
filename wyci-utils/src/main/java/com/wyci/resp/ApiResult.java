package com.wyci.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * 统一响应消息报文
 *
 */
@Data
@ToString
public class ApiResult<T> implements Serializable {

    private int code = 0;

    private String message;

    private T data;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Date timestamp;

    private String errorInfo;

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

    public ApiResult(int code, String message, T data, int status, String errorInfo) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
        this.timestamp = new Date();
        this.errorInfo = errorInfo;
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
        return success(code, message, data, ApiConstant.DEFAULT_HTTP_STATUS_OK);
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
        return success(ApiConstant.DEFAULT_SUCCESS_MESSAGE);
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
        return fail(code, message, data, ApiConstant.DEFAULT_HTTP_STATUS_FAIL);
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
        return fail(ApiConstant.DEFAULT_FAIL_MESSAGE);
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
        return empty(code, message, ApiConstant.DEFAULT_HTTP_STATUS_NO_CONTENT);
    }

    public static <T> ApiResult<T> empty(String message) {
        return empty(ApiResultCode.NO_CONTENT.getCode(), message);
    }

    public static <T> ApiResult<T> empty() {
        return empty(ApiConstant.DEFAULT_NO_CONTENT_MESSAGE);
    }

    public static <T> ApiResult<T> empty(ApiResultCode resultCode) {
        return empty(resultCode.getCode(), resultCode.getMessage());
    }


    public static <T> ApiResult<T> error(String errorInfo) {
        return error(ApiResultCode.ERROR.getCode(), ApiConstant.DEFAULT_FAIL_MESSAGE, null, ApiConstant.DEFAULT_HTTP_STATUS_FAIL, errorInfo);
    }

    public static <T> ApiResult<T> error(int code, String errorInfo, T data) {
        return error(code, ApiConstant.DEFAULT_FAIL_MESSAGE, data, ApiConstant.DEFAULT_HTTP_STATUS_FAIL, errorInfo);
    }

    public static <T> ApiResult<T> error(int code, String message, String errorInfo) {
        return error(code, message, null, ApiConstant.DEFAULT_HTTP_STATUS_FAIL, errorInfo);
    }


    public static <T> ApiResult<T> error(ApiResultCode resultCode, T data, String errorInfo) {
        return error(resultCode.getCode(), resultCode.getMessage(), data, ApiConstant.DEFAULT_HTTP_STATUS_FAIL, errorInfo);
    }

    public static <T> ApiResult<T> error(ApiResultCode resultCode, int httpStatus, String errorInfo) {
        return error(resultCode.getCode(), resultCode.getMessage(), null, httpStatus, errorInfo);
    }

    public static <T> ApiResult<T> error(int code, String message, T data, int httpStatus, String errorInfo) {
        return new ApiResult<>(code, message, data, httpStatus, errorInfo);
    }


    @JsonIgnore
    public boolean isSuccess() {
        return this.status == 200 || this.code == ApiResultCode.SUCCESS.getCode();
    }
}
