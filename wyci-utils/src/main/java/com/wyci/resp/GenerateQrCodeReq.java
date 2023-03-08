package com.wyci.resp;

import lombok.Data;
import lombok.ToString;

/**
 * @Description 生成二维码请求
 * @Author wangshuo
 * @Date 2023-03-08 15:29
 * @Version V1.0
 */
@Data
@ToString
public class GenerateQrCodeReq {

    /**
     * 生成内容
     */
    private String content;

    /**
     * log地址
     */
    private String logPath;

    /**
     * 压缩LOG(0:压缩,1不压缩)
     */
    private String compress;


    /**
     * 宽度
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;


}
