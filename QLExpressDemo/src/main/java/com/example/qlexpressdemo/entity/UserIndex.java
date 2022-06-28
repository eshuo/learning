package com.example.qlexpressdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.ql.util.express.annotation.QLAlias;
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
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_index")
@ApiModel(value = "UserIndex对象", description = "")
@QLAlias("指标")
public class UserIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @QLAlias("用户Id")
    private String id;

    @QLAlias("用户名")
    private String userName;

    @QLAlias("安全信用")
    @TableField("safe_credit")
    private String safeCredit;

    @QLAlias("认证方式")
    @TableField("verification")
    private String verification;

    @QLAlias("设备")
    @TableField("device")
    private String device;

    @QLAlias("访问时间")
    @TableField("Interview_time")
    private String interviewTime;

    @QLAlias("锁定次数")
    @TableField("Lock_num")
    private String lockNum;


//    ....
}
