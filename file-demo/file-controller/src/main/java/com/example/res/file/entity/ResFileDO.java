package com.example.res.file.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @Description 图片
 * @Author wangshuo
 * @Date 2022-10-17 17:17
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("res_file")
public class ResFileDO implements Serializable {
    private static final long serialVersionUID = 5533071832213483708L;


    @Id
    @Field("id")
    private String id;

    private String url;

    private String rootUrl;

    private String type;

    private String contentType;

    private Long size;

    private String md5;

    private String status;


}
