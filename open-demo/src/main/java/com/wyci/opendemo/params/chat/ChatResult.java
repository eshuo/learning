package com.wyci.opendemo.params.chat;

import com.wyci.opendemo.params.chat.model.ChoiceModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "聊天返回结果")
public class ChatResult {
    @ApiModelProperty("chat-id")
    private String id;

    @ApiModelProperty("调用对象")
    private String object;

    @ApiModelProperty("创建ID")
    private Long created;

    @ApiModelProperty("使用模型")
    private String model;

    @ApiModelProperty("token消耗")
    private Map<String,String> usage;

    // 可选结果集合
    @ApiModelProperty("可选结果集合")
    List<ChoiceModel> choices;
}
