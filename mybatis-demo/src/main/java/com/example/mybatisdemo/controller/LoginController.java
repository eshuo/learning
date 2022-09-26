package com.example.mybatisdemo.controller;


import com.example.mybatisdemo.dao.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2022-09-24
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;


    @ResponseBody
    @RequestMapping("/init")
    public Map<String, List<Map<String, Object>>> init(){
        return iLoginService.init();
    }

}
