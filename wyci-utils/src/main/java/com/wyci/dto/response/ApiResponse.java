package com.wyci.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wyci.exception.message.BaseMessage;
import com.wyci.handle.ContextHandler;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * @Description 统一响应
 * @Author wangshuo
 * @Date 2025-03-26 19:44
 * @Version V1.0
 */
@Data
@ToString
public class ApiResponse<T> implements Serializable {


    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String message;


    /**
     * 响应状态
     */
    @ApiModelProperty(value = "响应状态")
    private int status;

    /**
     * 响应时间
     */
    @ApiModelProperty(value = "响应时间yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private final Date timestamp;


    /**
     * 响应ID
     */
    @ApiModelProperty(value = "响应时间yyyy-MM-dd HH:mm:ss")
    private String threadId;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;


    public ApiResponse(String message, int status, String threadId, T data) {
        this.message = message;
        this.status = status;
        this.timestamp = new Date();
        this.data = data;
        this.threadId = threadId;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(BaseMessage.SUCCESS.getMessage(), BaseMessage.SUCCESS.getCode(), ContextHandler.getThreadId(), data);
    }

    public static <T> ApiResponse<T> error(T data) {
        return new ApiResponse<>("处理失败", BaseMessage.ERROR.getCode(), ContextHandler.getThreadId(), data);
    }


    public static ApiResponse<String> error(String message, int status) {
        return new ApiResponse<>(message, status, ContextHandler.getThreadId(), null);
    }
}
