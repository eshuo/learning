package com.example.qlexpressdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("param_info")
@ApiModel(value = "ParamInfo对象", description = "")
public class ParamInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "字段")
    private String field;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "数据类型")
    private String type;

    @ApiModelProperty(value = "类信息")
    private String cInfo;

    public ParamInfo(String id, String field, String title) {
        this.id = id;
        this.field = field;
        this.title = title;
    }


    //    @ApiModelProperty(value = "类方法")
//    private String cMethod;


//    方法

//    用于将一个用户自己定义的对象(例如Spring对象)方法转换为一个表达式计算的函数
//    runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(), "upper", new String[] {"String"}, null);


}
