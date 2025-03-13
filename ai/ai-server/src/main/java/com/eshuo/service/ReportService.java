package com.eshuo.service;

import com.eshuo.dao.Report;
import com.eshuo.dao.ReportComment;
import com.eshuo.dto.request.RequestPage;
import com.eshuo.dto.response.ResponsePage;

/**
 * @Description 报告服务
 * @Author wangshuo
 * @Date 2025-02-27 21:18
 * @Version V1.0
 */
public interface ReportService {


    /**
     * 分页查询
     *
     * @param requestPage
     * @return
     */
    public ResponsePage<Report> page(RequestPage<Report> requestPage);


    /**
     * 添加评论
     *
     * @param reportComment
     */
    public void add(ReportComment reportComment);


    /**
     * 获取报告+评论
     *
     * @param id
     * @return
     */
    public Report info(String id);

}
