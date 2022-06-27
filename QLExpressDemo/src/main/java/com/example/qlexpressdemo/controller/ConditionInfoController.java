package com.example.qlexpressdemo.controller;


import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.service.IConditionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 条件表 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
@RestController
@RequestMapping("/condition-info")
public class ConditionInfoController {

    @Autowired
    private IConditionInfoService iConditionInfoService;


    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody ConditionInfo conditionInfo) {
        return iConditionInfoService.save(conditionInfo);
    }


    @RequestMapping("/ok")
    @ResponseBody
    public String hello() {
        return "OK";
    }


}
