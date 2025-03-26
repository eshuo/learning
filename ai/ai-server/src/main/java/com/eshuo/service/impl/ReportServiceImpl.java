package com.eshuo.service.impl;

import com.eshuo.dao.Report;
import com.eshuo.dao.ReportComment;
import com.eshuo.dto.request.RequestPage;
import com.eshuo.dto.response.ResponsePage;
import com.eshuo.repository.ReportCommentRepository;
import com.eshuo.repository.ReportRepository;
import com.eshuo.service.ReportService;
import com.eshuo.utils.IdGenerator;
import javax.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-02-27 22:10
 * @Version V1.0
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportRepository repository;


    @Resource
    private ReportCommentRepository reportCommentRepository;


    @Override
    public ResponsePage<Report> page(RequestPage<Report> requestPage) {
        Report queryData = requestPage.getQueryData();
        if(null ==queryData){
            queryData = new Report();
        }
        return new ResponsePage<>(repository.findAll(Example.of(queryData), requestPage.toPageRequest()));
    }

    @Override
    public void add(ReportComment reportComment) {
        reportComment.setId(IdGenerator.nextId(ReportComment.class));
        reportComment.setCreateTime(System.currentTimeMillis());
        reportCommentRepository.save(reportComment);
    }

    @Override
    public Report info(String id) {
        return repository.findById(id).orElse(null);
    }
}
