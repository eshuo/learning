package com.example.res.file.service.impl;

import com.example.res.file.domain.mongo.ResFileRepository;
import com.example.res.file.entity.ResFileDO;
import com.example.res.file.exception.ResFileException;
import com.example.res.file.propertie.ResFileProperties;
import com.example.res.file.service.ResFileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-17 17:34
 * @Version V1.0
 */
@Service
@EnableConfigurationProperties(ResFileProperties.class)
public class ResFileServiceImpl implements ResFileService {
    private static final long serialVersionUID = -4984772866023269034L;

    private final static Logger logger = LoggerFactory.getLogger(ResFileServiceImpl.class);

    @Resource
    private ResFileRepository resFileRepository;

    private final Path resFileRootPath;

    public ResFileServiceImpl(ResFileProperties resFileProperties) {
        try {
            this.resFileRootPath = System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS") ?
                    Paths.get(resFileProperties.getWinRootPath()) : Paths.get(resFileProperties.getLinuxRootPath());
            Files.createDirectories(this.resFileRootPath);
        } catch (Exception ex) {
            logger.error("resFileRootPath error", ex);
            throw new ResFileException("无法创建存储上传文件的目录");
        }

    }

    /**
     * 上传
     *
     * @param file 文件
     * @return {@code ImageDO}
     */
    @Override
    public ResFileDO upload(MultipartFile file) {
        if (null == file || file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new ResFileException("保存文件失败,上传文件不存在!");
        }
        ResFileDO resFileDO = new ResFileDO();
        try {

            final String fileName = file.getOriginalFilename();
            if (fileName.contains(".")) {
                resFileDO.setType(fileName.substring(fileName.lastIndexOf(".")).replaceAll("\\.", ""));
            } else {
                resFileDO.setType("unknown");
            }

            Date date = new Date();
            final Calendar calendar = DateUtils.toCalendar(date);
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            String monthDay = String.format("%02d%02d", calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));


            String url = "/" + calendar.get(Calendar.YEAR) + "/" + monthDay + "/";
            final Path resolve = Paths.get(resFileRootPath.toString(), url);
            Files.createDirectories(resolve);
            final String fileIdName = id + "." + resFileDO.getType();
            url = url + fileIdName;
            final Path toFilePath = Paths.get(resolve.toString(), fileIdName);
            file.transferTo(toFilePath);
            final String toUrl = toFilePath.toString().replaceAll("\\\\+", "/");
            resFileDO.setSize(file.getSize());
            resFileDO.setMd5(DigestUtils.md5DigestAsHex(file.getInputStream()));
            resFileDO.setContentType(file.getContentType());
            resFileDO.setUrl(url);
            resFileDO.setRootUrl(toUrl);
            resFileDO.setId(id);
            return resFileRepository.save(resFileDO);
        } catch (IOException e) {
            logger.error("upload error ", e);
            throw new ResFileException("保存文件 " + file + "失败");
        }
    }

    /**
     * 上传
     *
     * @param files 文件
     * @return {@code ImageDO}
     */
    @Override
    public List<ResFileDO> uploads(MultipartFile[] files) {
        return null;
    }

    /**
     * 发现通过id
     *
     * @param id id
     * @return {@code ResFileDO}
     */
    @Override
    public ResFileDO findById(String id) {
        return resFileRepository.findById(id).orElse(null);
    }

    /**
     * 下载
     *
     * @param id id
     * @return {@code ResponseEntity<Resource>}
     */
    @Override
    public ResponseEntity<? extends org.springframework.core.io.Resource> download(String id) {
        final ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
        final ResFileDO byId = findById(id);
        if (null == byId) {
            return bodyBuilder.body(null);
        }

        Path path = Paths.get(byId.getRootUrl());
        org.springframework.core.io.Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
            bodyBuilder.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
        } catch (IOException e) {
            logger.error("download error ", e);
        }

        return bodyBuilder
                .contentLength(byId.getSize())
                .contentType(MediaType.parseMediaType(byId.getContentType()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
