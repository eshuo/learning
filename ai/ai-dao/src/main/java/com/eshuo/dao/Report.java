package com.eshuo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 * @Description 报告
 * @Author wangshuo
 * @Date 2025-02-26 20:14
 * @Version V1.0
 */
@Data
@Entity
@Table(name = "report")
public class Report implements Serializable {


    @Id
    @Column(nullable = false, length = 32)
    private String id;

    /**
     * 会话ID
     */
    @Column(length = 128)
    private String conversationId;


    /**
     * 标题
     */
    private String title;


    /**
     * 状态
     */

    private Integer status;


    /**
     * 下载地址
     */
    @Column(length = 512)
    private String url;


    /**
     * 类型
     */
    private String type;


    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 内容
     */
    @Lob
    private String body;

    /**
     * 评论
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reportId")
    private List<ReportComment> reportCommentList;

}
