package com.wyci.opendemo.params.chat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kindear
 * 聊天信息参数
 */
@Data
@ApiModel(value = "聊天信息")
public class ChatMessage {
    @ApiModelProperty(value = "聊天角色",example = "user")
    String role;

    @ApiModelProperty(value = "聊天内容",example = "hello")
    String content;
}
