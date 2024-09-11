package com.wyci.webdemo.demos.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wyci.webdemo.demos.domain.EasyExcelDemo;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-09-11 11:23
 * @Version V1.0
 */
public class EasyExcelDemoService {

    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("文件名", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), EasyExcelDemo.class).excelType(ExcelTypeEnum.XLSX).sheet("sheetName")
                .doWrite(new ArrayList<>());


        } catch (Exception e) {

            throw new RuntimeException(e);
        }


    }

}
