package com.wyci.opendemo.params.image;

import com.wyci.opendemo.params.image.model.ImageChoiceModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "图像编辑结果")
public class ImageResult {
    @ApiModelProperty("创建ID")
    private Long created;

    @ApiModelProperty("结果列表")
    private List<ImageChoiceModel> data;
}
