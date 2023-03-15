package com.eshuo.websocketdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-15 14:13
 * @Version V1.0
 */
@RestController
@RequestMapping("/demo")
public class DemoService {


    @Autowired
    private AlarmOccurWS alarmOccurWS;


    @GetMapping("/hello/{id}/{msg}")
    public String hello(@PathVariable("id") String id, @PathVariable("msg") String msg) {
        alarmOccurWS.sendMessageTo(msg, id);
        return "ok";
    }


}
