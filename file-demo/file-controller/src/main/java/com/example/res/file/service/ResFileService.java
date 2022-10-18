package com.example.res.file.service;

import com.example.res.file.entity.ResFileDO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @Description images
 * @Author wangshuo
 * @Date 2022-10-17 17:26
 * @Version V1.0
 */
public interface ResFileService extends Serializable {

    /**
     * 上传
     *
     * @param file 文件
     * @return {@code ImageDO}
     */
    ResFileDO upload(@RequestParam("file") MultipartFile file);

    /**
     * 上传
     *
     * @param files 文件
     * @return {@code ImageDO}
     */
    List<ResFileDO> uploads(@RequestParam("files") MultipartFile[] files);


    /**
     * 发现通过id
     *
     * @param id id
     * @return {@code ResFileDO}
     */
    ResFileDO findById(@RequestParam("id") String id);


    /**
     * 下载
     *
     * @param id id
     * @return {@code ResponseEntity<Resource>}
     */
    ResponseEntity<? extends Resource> download(@RequestParam("id") String id);


}
