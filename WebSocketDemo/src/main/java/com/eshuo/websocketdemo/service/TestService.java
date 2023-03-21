package com.eshuo.websocketdemo.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-15 15:32
 * @Version V1.0
 */
@RestController
@RequestMapping(value = "/route/{robotId}")
public class TestService {


    @GetMapping()
    public String hello(@PathVariable("robotId") String robotId) {
        return robotId;
    }


}
