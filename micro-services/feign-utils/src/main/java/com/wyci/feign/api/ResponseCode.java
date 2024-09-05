package com.wyci.feign.api;

/**
 * 通用返回code值
 * @author
 */
public enum ResponseCode {
    //系统通用
    SUCCESS(200,"成功"),

    //非业务异常，统一500处理
    ERROR(500,"失败"),
    
    SERVICE_UNAVAILABLE(503,"服务未找到"),

    UNAUTHORIZED(401,"请重新登录"),

    NOT_FOUND(404, "Not Found")
    ;

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
