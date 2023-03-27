package com.wyci.opendemo.params.image.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "图片返回结果")
public class ImageChoiceModel {
    String url;
}
