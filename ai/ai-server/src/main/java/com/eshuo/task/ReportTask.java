package com.eshuo.task;

import com.eshuo.dao.Report;
import com.eshuo.dto.response.BlockResponse;
import com.eshuo.repository.ReportRepository;
import com.eshuo.service.DifyService;
import com.eshuo.utils.IdGenerator;
import com.eshuo.utils.date.DateUtil;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Description 报告定时器
 * @Author wangshuo
 * @Date 2025-02-27 21:18
 * @Version V1.0
 */
@Service
public class ReportTask {


    @Value("${bric.report.api}")
    private String reportApi;


    @Value("${bric.report.secret}")
    private String reportKey;


    @Resource
    private DifyService difyService;


    @Resource
    private ReportRepository repository;


    //每天23点50分执行一次
    @Scheduled(cron = "0 50 23 * * ?")
//    @Scheduled(fixedRate = 3000)
    public void reportTask() {
        Date date = new Date();
        String titleDate = DateUtil.getDate(date);
        String title = String.format("%s日报", titleDate);
        BlockResponse response = difyService.blockingMessage(title+",返回html格式的内容", "report", reportKey, reportApi, "");
        if (null != response) {
            Report report = new Report();
            report.setId(IdGenerator.nextId(Report.class));
            report.setTitle(title);
            report.setConversationId(response.getConversationId());
            report.setCreateTime(date.getTime());
            String answer = response.getAnswer();
            String substring = null;
            if (answer.contains("```")) {
                substring = answer.substring(answer.indexOf("```"), answer.lastIndexOf("```") + 3);
                //处理md
                if (substring.startsWith("```markdown")) {
//                    substring = substring.replaceFirst("markdown", "");
                    report.setType("markdown");
                } else if (substring.startsWith("```html")) {
                    substring = substring.replaceFirst("```html", "").replace("```", "");
                    report.setType("html");
                }
            } else {
                substring = answer.substring(answer.indexOf("details>") + 8);
            }

            report.setBody(substring);
            repository.save(report);
        }


    }


}
