package com.wyci.opendemo.params.edit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "编辑返回结果")
public class EditChoiceModel {
    @ApiModelProperty(value = "结果下标")
    Integer index;

    @ApiModelProperty(value = "返回内容",example = "hello")
    String text;
}
