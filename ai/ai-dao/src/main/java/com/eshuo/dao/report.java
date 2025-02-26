package com.eshuo.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class report implements Serializable {


    @Id
    @Column(nullable = false, length = 32)
    private String id;

//    文章   内容   时间   次数?      下载地址? 状态?  评论


}
