package com.example.qlexpressdemo.controller;


import com.example.qlexpressdemo.entity.RuleInfo;
import com.example.qlexpressdemo.service.IRuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
@RestController
@RequestMapping("/rule-info")
public class RuleInfoController {

    @Autowired
    private IRuleInfoService iRuleInfoService;


    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody RuleInfo ruleInfo) {
        final boolean save = iRuleInfoService.save(ruleInfo);
        return save;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody RuleInfo ruleInfo) {
        return iRuleInfoService.updateById(ruleInfo);
    }


    @RequestMapping("/del")
    @ResponseBody
    public boolean delete(@PathVariable("id") String id) {
        final boolean b = iRuleInfoService.removeById(id);
        return b;
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public RuleInfo get(@PathVariable("id") String id) {
        return iRuleInfoService.getById(id);
    }

    @RequestMapping("/ok")
    @ResponseBody
    public String hello() {
        return "OK";
    }
}
