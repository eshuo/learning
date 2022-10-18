package com.example.res.file.controller;

import com.example.res.file.entity.ResFileDO;
import com.example.res.file.service.ResFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Description 图片
 * @Author wangshuo
 * @Date 2022-10-17 17:10
 * @Version V1.0
 */
@Api(tags = "文件管理")
@RestController
@RequestMapping("/manage/api/v1.0/file")
public class FileController {

    @Resource
    private ResFileService resFileService;

    @ApiOperation(value = "上传")
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ResponseBody
    public ResFileDO upload(@RequestParam("file") MultipartFile file) {
        return resFileService.upload(file);
    }


    @ApiOperation("下载")
    @RequestMapping(path = "/download")
    public ResponseEntity<? extends org.springframework.core.io.Resource> download(@RequestParam("id") String id) {
        return resFileService.download(id);
    }


}
