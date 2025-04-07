package com.wyci.dto.request;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @Description 统一请求
 * @Author wangshuo
 * @Date 2025-03-26 19:46
 * @Version V1.0
 */
@Data
@ToString
public class ApiRequest<T> implements Serializable {


    @ApiModelProperty(value = "防重放毫秒级时间戳")
    private Long timestamp;

    @ApiModelProperty(value = "防重放随机数")
    private String nonce;

    @ApiModelProperty(value = "签名结果")
    private String sign;

    @ApiModelProperty(value = "业务数据")
    private T data;


}
