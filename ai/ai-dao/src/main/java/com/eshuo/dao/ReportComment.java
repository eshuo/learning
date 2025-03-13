package com.eshuo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;

/**
 * @Description 报告评论
 * @Author wangshuo
 * @Date 2025-02-26 20:20
 * @Version V1.0
 */
@Data
@Entity
@Table(name = "report_comment")
public class ReportComment {

    @Id
    @Column(nullable = false, length = 32)
    private String id;


    /**
     * 评论文章ID
     */
    private String reportId;

    /**
     * 评论人
     */
    private String userName;

    /**
     * 评论内容
     */
    @Lob
    private String body;


    /**
     * 状态
     */
    private String status;


    /**
     * 评论时间
     */
    private Long createTime;


}
