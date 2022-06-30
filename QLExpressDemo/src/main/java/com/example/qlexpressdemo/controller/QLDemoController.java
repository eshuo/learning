package com.example.qlexpressdemo.controller;

import com.example.qlexpressdemo.bean.rest.QLDemo;
import com.example.qlexpressdemo.service.QLDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-28 15:42
 * @Version V1.0
 */
@RestController
@RequestMapping("/demo")
public class QLDemoController {


    @Autowired
    private QLDemoService qlDemoService;

    @RequestMapping("/verify")
    @ResponseBody
    public boolean verify(@RequestBody QLDemo.verify verify) {
        return qlDemoService.verify(verify);
    }


    @RequestMapping("/check")
    @ResponseBody
    public boolean check(@RequestBody QLDemo.verify verify) {
        try {
            return qlDemoService.check(verify);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
