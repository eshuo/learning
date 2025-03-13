package com.eshuo.api.controller;

import com.eshuo.dao.Report;
import com.eshuo.dao.ReportComment;
import com.eshuo.dto.request.RequestPage;
import com.eshuo.dto.response.ResponsePage;
import com.eshuo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-02-27 23:42
 * @Version V1.0
 */
@RestController
@RequestMapping("/data")
public class ReportController {


    @Autowired
    private ReportService reportService;

    /**
     * 分页查询
     *
     * @param requestPage
     * @return
     */
    @PostMapping("/page")
    public ResponsePage<Report> page(@RequestBody RequestPage<Report> requestPage) {
        return reportService.page(requestPage);
    }


    /**
     * 添加评论
     *
     * @param reportComment
     */
    @PostMapping("/add")
    public void add(@RequestBody ReportComment reportComment) {
        reportService.add(reportComment);
    }


    /**
     * 获取报告+评论
     *
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Report info(@PathVariable("id") String id) {
        return reportService.info(id);
    }


}
