package com.wyci.mogodbdemo.controller;

import com.wyci.mogodbdemo.entity.User;
import com.wyci.mogodbdemo.entity.UserDynamic;
import com.wyci.mogodbdemo.service.UserDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-29 14:06
 * @Version V1.0
 */
@RestController
@RequestMapping("/dynamic")
public class UserDynamicController {


    @Autowired
    private UserDynamicService userDynamicService;

    @RequestMapping("/save")
    @ResponseBody
    public UserDynamic save(@RequestBody UserDynamic userDynamic) {
        return userDynamicService.defaultSave(userDynamic);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public UserDynamic get(@PathVariable("id") String id) {
        return userDynamicService.find(id);
//        return iConditionInfoService.getById(id);
    }


    @GetMapping("/gets")
    @ResponseBody
    public List<UserDynamic> get() {
        return userDynamicService.findAll();
//        return iConditionInfoService.getById(id);
    }

}
