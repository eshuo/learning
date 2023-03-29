package com.example.tokenuserdemo.demos.domanin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * @author WangShuo
 */
@ToString
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 序列号
     */
    private Long serialNumber;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 全拼音登录名
     */
    private String allPinYinLoginName;

    /**
     * 第一个拼音登录名
     */
    private String firstPinYinLoginName;

    /**
     * 全拼音用户名
     */
    private String allPinYinUserName;

    /**
     * 第一个拼音用户名
     */
    private String firstPinYinUserName;


    @JsonIgnore
    private String password;

    /**
     * 加密密码
     */
    private String cryptedPassword;

    /**
     * 性别
     */
    private String gender;


    /**
     * 电子邮件
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 身份证号码
     */
    private String idCardNumber;

    /**
     * 级别
     */
    private String secLevel;

    /**
     * 显示编号
     */
    private String showNumber;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 最后改密码时间
     */
    private Date lastPawdTime;

    /**
     * 父名称
     */
    private String parentName;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 父类型
     */
    private Integer parentType;

    /**
     * 父id
     */
    private String parentId;



    /**
     * 角色id
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 应用程序id
     */
    private String appId;



    /**
     * 应用程序名称
     */
    private String[] appNames;

    /**
     * 管理授权
     */
    private Integer manageAuthorize; //管理访问授权



    private Object role;

    private String telephoneNumber;



    private Date lastPinTime;


    private Long createTime;


    private Date lastPwdTime;

}
