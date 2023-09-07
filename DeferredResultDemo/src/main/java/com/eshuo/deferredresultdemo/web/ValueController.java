package com.eshuo.deferredresultdemo.web;

import org.springframework.web.bind.annotation.*;

/**
 * @Description demo
 * @Author wangshuo
 * @Date 2023-09-07 10:09
 * @Version V1.0
 */
@RestController
@RequestMapping("/demo")
public class ValueController {


    /**
     * 不声明请求键名时,默认接收所有请求数据
     * @param demo
     */
    @RequestMapping(method = RequestMethod.POST,value = "/value")
    @ResponseBody
    public void demo(@RequestBody String demo){
        System.out.println(demo);
    }
}
