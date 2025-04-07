package com.wyci.exception.message;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-11 16:16
 * @Version V1.0
 */
public enum BaseMessage implements BaseMessageInterface {


    SUCCESS(200, "处理成功"),
    //认证服务
    AUTH_COMMON_ERROR(400, "%s"),
    AUTH_INVALID_AUTHENTICATION_INFORMATION(401, "非法认证信息"),
    AUTH_LOGIN_NAME_OR_PAWD_ERROR(402, "账号名或者密码错误"),
    AUTH_USER_INVALID(403, "无效的用户"),

    ERROR(500, "处理失败 %s"),
    PARAMETER_ERROR(501, "参数错误 %s"),
    PARAMETER_NOT_NULL(502, " %s 参数不能为空！"),
    OPERATION_OBJECT_NOT_EXIST(503, "操作对象不存在！%s"),
    PARAMETER_INVALID(504, "参数无效，%s"),
    COMMON_ERROR(505, " %s "),
    ;

    private final int code;

    private final String message;


    BaseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return BASE_CODE + code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
