package com.wyci.gabapidemo.demos.gab.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 请求
 * @Author wangshuo
 * @Date 2023-09-07 16:49
 * @Version V1.0
 */
@Schema(description = "请求")
public interface MockRequest {


    /**
     * 新增信息
     */
    @Schema(description = "新增信息")
    @Data
    class AddMessage {
        /**
         * 消息标题
         */
        @Schema(description = "消息标题")
        private String messageTitle;

        /**
         * 发送人用户id
         */
        @Schema(description = "发送人用户id")
        private String sendUserId;

        /**
         * 发送人用户姓名
         */
        @Schema(description = "发送人用户姓名")
        private String sendUserName;

        /**
         * 发送人用户编码
         */
        @Schema(description = "发送人用户编码")
        private String sendUserCode;

        /**
         * 子系统编码oa
         */
        @Schema(description = "子系统编码oa")
        private String appCode;

        /**
         * 子系统名称OA
         */
        @Schema(description = "子系统名称OA")
        private String appName;

        /**
         * 模块名称：公文管理 督办管理 值班管理 加班管理 建议提案 会议管理领导日程 资产管理 业务审批行政管理 人事管理 党建管理财务管理 任务督办 日程活动
         */
        @Schema(description = "模块名称：公文管理 督办管理 值班管理 加班管理 建议提案 会议管理领导日程 资产管理 业务审批行政管理 人事管理 党建管理财务管理 任务督办 日程活动")
        private String modularName;

        /**
         * 创建时间(YYYY-mm-dd hh:mm:ss)
         */
        @Schema(description = "创建时间(YYYY-mm-dd hh:mm:ss)")
        private String createTime;

        /**
         * 消息类型-1 待办 2 通知 0 全部
         */
        @Schema(description = "消息类型-1 待办 2 通知 0 全部")
        private String type;

        /**
         * 接收人用户id
         */
        @Schema(description = "接收人用户id")
        private String receiveUserId;

        /**
         * 接收人用户姓名
         */
        @Schema(description = "接收人用户姓名")
        private String receiveUserName;

        /**
         * 接收人用户编码
         */
        @Schema(description = "接收人用户编码")
        private String receiveUserCode;

        /**
         * 待办事项表单回调url（如果是待办必填）
         */
        @Schema(description = "待办事项表单回调url（如果是待办必填）")
        private String url;

        /**
         * 已办事项表单回调url
         */
        @Schema(description = "已办事项表单回调url")
        private String doneUrl;

        /**
         * 待办事项表单回调url（OA内部url）
         */
        @Schema(description = "待办事项表单回调url（OA内部url）")
        private String innerUrl;

        /**
         * 已办事项表单回调url（OA内部url）
         */
        @Schema(description = "已办事项表单回调url（OA内部url）")
        private String innerDoneUrl;

        /**
         * 表单id（业务主键id
         */
        @Schema(description = "表单id（业务主键id")
        private String orderId;
    }
}
