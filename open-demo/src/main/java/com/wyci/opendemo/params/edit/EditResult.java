package com.wyci.opendemo.params.edit;

import com.wyci.opendemo.params.edit.model.EditChoiceModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "编辑返回结果")
public class EditResult {
    @ApiModelProperty("chat-id")
    private String id;

    @ApiModelProperty("调用对象")
    private String object;

    @ApiModelProperty("创建ID")
    private Long created;


    @ApiModelProperty("token消耗")
    private Map<String,String> usage;

    // 可选结果集合
    @ApiModelProperty("可选结果集合")
    List<EditChoiceModel> choices;
}
