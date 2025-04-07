package com.wyci.exception;

import com.wyci.exception.message.BaseMessage;
import com.wyci.exception.message.BaseMessageInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-12-19 14:28
 * @Version V1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -6211380098221773998L;

    /**
     * 错误码 {@link BaseMessageInterface}
     */
    private int errorCode;


    /**
     * 错误信息
     */
    private String errorInfo;


    private HttpStatus status;


    public BaseException() {

    }

    public BaseException(HttpStatus status) {
        this.status = status;
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message) {
        this(BaseMessage.COMMON_ERROR, message);
    }


    public BaseException(BaseMessageInterface message, Throwable cause, Object... parameters) {
        super(String.format(message.getMessage(), parameters), cause);
        this.errorInfo = String.format(message.getMessage(), parameters);
        this.errorCode = message.getCode();

    }

    public BaseException(BaseMessageInterface message) {
        super(message.getMessage());
        this.errorInfo = message.getMessage();
        this.errorCode = message.getCode();
    }

    public BaseException(BaseMessageInterface message, Object... parameters) {
        super(String.format(message.getMessage(), parameters));
        this.errorInfo = String.format(message.getMessage(), parameters);
        this.errorCode = message.getCode();
    }

    public BaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorInfo = errorMsg;
        this.errorCode = errorCode;
    }

    public BaseException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorInfo = errorMsg;
        this.errorCode = errorCode;
    }
}
