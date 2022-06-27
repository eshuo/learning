package com.example.qlexpressdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 条件表
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("condition_info")
@ApiModel(value = "ConditionInfo对象", description = "条件表")
public class ConditionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "表达式 ")
    private String expression;

    @ApiModelProperty(value = "自引用id ")
    private Integer parentId;

    @ApiModelProperty(value = "响应信息")
    private String resultInfo;

    private String contextInfo;

//    @TableField(typeHandler = com.example.qlexpressdemo.handler.IntegerListTypeHandler.class)
    private List<Integer> paramInfoIds;

    private Integer ruleId;


    /**
     * 子级
     */
    private List<ConditionInfo> subConditionInfos;


    /**
     * 参数集合
     */
    private List<ParamInfo> paramInfos;

//
//    /**
//     * 父级
//     */
//    private ConditionInfo parentConditionInfo;


}
