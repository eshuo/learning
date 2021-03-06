package com.example.qlexpressdemo.controller;


import com.example.qlexpressdemo.entity.ParamInfo;
import com.example.qlexpressdemo.service.IParamInfoService;
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
@RequestMapping("/param-info")
public class ParamInfoController {


    @RequestMapping("/ok")
    @ResponseBody
    public String hello() {
        return "OK";
    }


    @Autowired
    private IParamInfoService iParamInfoService;


    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody ParamInfo info) {
        final boolean save = iParamInfoService.save(info);
        return save;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody ParamInfo info) {
        return iParamInfoService.updateById(info);
    }


    @RequestMapping("/del")
    @ResponseBody
    public boolean delete(@PathVariable("id") String id) {
        final boolean b = iParamInfoService.removeById(id);
        return b;
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ParamInfo get(@PathVariable("id") String id) {
        return iParamInfoService.getById(id);
    }


}
