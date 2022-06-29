package com.example.qlexpressdemo.controller;


import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.service.IConditionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody ConditionInfo info) {
        return iConditionInfoService.updateById(info);
    }


    @RequestMapping("/del")
    @ResponseBody
    public boolean delete(@PathVariable("id") String id) {
        final boolean b = iConditionInfoService.removeById(id);
        return b;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ConditionInfo get(@PathVariable("id") String id) {
        return iConditionInfoService.findById(id);
//        return iConditionInfoService.getById(id);
    }


    @RequestMapping("/ok")
    @ResponseBody
    public String hello() {
        return "OK";
    }


}
