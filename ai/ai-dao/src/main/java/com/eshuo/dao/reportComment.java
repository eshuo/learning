package com.eshuo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class reportComment {

//    评论  文章ID   评论人    评论内容   评论时间

    @Id
    @Column(nullable = false, length = 32)
    private String id;






}
