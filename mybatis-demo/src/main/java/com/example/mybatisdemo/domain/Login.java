package com.example.mybatisdemo.domain;

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
 * @author wangshuo
 * @since 2022-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("login")
@ApiModel(value="Login对象", description="")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String time;

    private String ip;

    private String status;

    private String loginName;

    private String email;


}
