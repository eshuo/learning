package com.example.qlexpressdemo.controller;


import com.example.qlexpressdemo.entity.UIndex;
import com.example.qlexpressdemo.service.IUIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-06-29
 */
@RestController
@RequestMapping("/u-index")
public class UIndexController {

    @RequestMapping("/ok")
    @ResponseBody
    public String hello() {
        return "OK";
    }


    @Autowired
    private IUIndexService iUIndexService;


    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody UIndex info) {
        final boolean save = iUIndexService.save(info);
        return save;
    }

    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody UIndex info) {
        return iUIndexService.updateById(info);
    }


    @RequestMapping("/del")
    @ResponseBody
    public boolean delete(@PathVariable("id") String id) {
        final boolean b = iUIndexService.removeById(id);
        return b;
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public UIndex get(@PathVariable("id") String id) {
        return iUIndexService.findById(id);
    }

}
