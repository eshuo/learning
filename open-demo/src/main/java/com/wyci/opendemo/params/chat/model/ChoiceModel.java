package com.wyci.opendemo.params.chat.model;

import com.wyci.opendemo.params.chat.ChatMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "结果集")
public class ChoiceModel {
    @ApiModelProperty(value = "结果下标")
    Integer index;

    @ApiModelProperty(value = "终止原因")
    String finish_reason;

    @ApiModelProperty(value = "可选结果")
    ChatMessage message;
}
