package com.example.qlexpressdemo.controller;


import com.example.qlexpressdemo.entity.UserIndex;
import com.example.qlexpressdemo.service.IUserIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/user-index")
public class UserIndexController {


    @RequestMapping("/ok")
    @ResponseBody
    public String hello() {
        return "OK";
    }


    @Autowired
    private IUserIndexService iUserIndexService;


    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody UserIndex info) {
        final boolean save = iUserIndexService.save(info);
        return save;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody UserIndex info) {
        return iUserIndexService.updateById(info);
    }


    @RequestMapping("/del")
    @ResponseBody
    public boolean delete(@PathVariable("id") String id) {
        final boolean b = iUserIndexService.removeById(id);
        return b;
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public UserIndex get(@PathVariable("id") String id) {
        return iUserIndexService.getById(id);
    }

}
