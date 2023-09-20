package com.wyci.gabapidemo.demos.gab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description 响应 @Author wangshuo @Date 2023-09-08 14:34 @Version V1.0
 */
@Schema(description = "响应")
public interface MockResponse {

  /**
   * 基本响应
   *
   * @param <T>
   */
  @Schema(description = "基本响应")
  @Data
  class BaseResponse<T> {

    /** 200 成功 500 失败 */
    @Schema(description = "200 成功 500 失败")
    private int code = 200;

    /** 返回信息 */
    @Schema(description = "返回信息")
    private String message = "测试返回信息";

    /** 响应数据 */
    @Schema(description = "响应数据")
    private List<T> data;

    public BaseResponse(List<T> data) {
      this.data = data;
    }
  }

  @Schema(description = "基本响应")
  @Data
  class BasePageResponse<T> {

    /** 200 成功 500 失败 */
    @Schema(description = "200 成功 500 失败")
    private int code = 200;

    @Schema(description = "总数")
    private int total;

    /** 返回信息 */
    @Schema(description = "返回信息")
    private String message = "测试返回信息";

    /** 响应数据 */
    @Schema(description = "响应数据")
    private List<T> rows;

    public BasePageResponse(List<T> rows) {
      this.rows = rows;
      this.total = rows.size();
    }
  }

  /** 代办消息响应 */
  @Schema(description = "代办消息响应")
  @Data
  class MessInfoResponse {
    /** 消息标题 */
    @Schema(description = "消息标题")
    private String messageTitle;

    /** 发送人用户id */
    @Schema(description = "发送人用户id")
    private String sendUserId;

    /** 发送人用户姓名 */
    @Schema(description = "发送人用户姓名")
    private String sendUserName;

    /** 发送人用户编码 */
    @Schema(description = "发送人用户编码")
    private String sendUserCode;

    /** 子系统编码 */
    @Schema(description = "子系统编码")
    private String appCode;

    /** 子系统名称 */
    @Schema(description = "子系统名称")
    private String appName;

    /** 模块名称 */
    @Schema(description = "模块名称")
    private String modularName;

    /** 创建时间(YYYY-mm-dd hh:mm:ss) */
    @Schema(description = "创建时间(YYYY-mm-dd hh:mm:ss)")
    private String createTime;

    /** 消息类型，1 待办 2 通知 */
    @Schema(description = "消息类型，1 待办 2 通知")
    private String type;

    /** 接收人用户id */
    @Schema(description = "接收人用户id")
    private String receiveUserId;

    /** 接收人用户姓名 */
    @Schema(description = "接收人用户姓名")
    private String receiveUserName;

    /** 接收人用户编码 */
    @Schema(description = "接收人用户编码")
    private String receiveUserCode;

    /** 待办事项表单回调url（如果是待办必填） */
    @Schema(description = "待办事项表单回调url（如果是待办必填）")
    private String url;

    /** 已办事项表单回调url */
    @Schema(description = "已办事项表单回调url")
    private String doneUrl;

    /** 待办事项表单回调url（OA内部url） */
    @Schema(description = "待办事项表单回调url（OA内部url）")
    private String innerUrl;

    /** 已办事项表单回调url（OA内部url） */
    @Schema(description = "已办事项表单回调url（OA内部url）")
    private String innerDoneUrl;

    /** 表单id（业务主键id） */
    @Schema(description = "表单id（业务主键id）")
    private String orderId;

    /** 是否有效 */
    @Schema(description = "是否有效")
    private String isValid;

    /** 修改人 */
    @Schema(description = "修改人")
    private String updateUser;

    /** 修改时间 */
    @Schema(description = "修改时间")
    private String updateTime;

    /** 消息状态（0未读 1已读） */
    @Schema(description = "消息状态（0未读 1已读）")
    private String state;

    /** 消息id */
    @Schema(description = "消息id")
    private String messageId;
  }

  /** 获取各个模块及待办和未读消息数量 */
  @Schema(description = "获取各个模块及待办和未读消息数量")
  @Data
  class GroupInfoNum {
    /** 模块名称 */
    @Schema(description = "模块名称")
    private String modularName;

    /** 消息数量 */
    @Schema(description = "消息数量")
    private String messageCount;

    /** 排序 */
    private String sort;
  }

  /** 获取各个模块及待办和未读消息数量 */
  @Schema(description = "获取各个模块及待办和未读消息数量")
  @Data
  class AppInfoNum {
    /** 系统编码 */
    @Schema(description = "系统编码")
    private String appCode;

    /** 消息数量 */
    @Schema(description = "消息数量")
    private String messageCount;
  }
}
