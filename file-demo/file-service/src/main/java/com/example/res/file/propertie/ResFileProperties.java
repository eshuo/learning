package com.example.res.file.propertie;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-17 17:36
 * @Version V1.0
 */
@ConfigurationProperties(prefix = "res.file")
public class ResFileProperties {

    /** 文件根路径 */
    private String linuxRootPath;


    private String winRootPath;


    public String getLinuxRootPath() {
        return linuxRootPath;
    }

    public void setLinuxRootPath(String linuxRootPath) {
        this.linuxRootPath = linuxRootPath;
    }


    public String getWinRootPath() {
        return winRootPath;
    }

    public void setWinRootPath(String winRootPath) {
        this.winRootPath = winRootPath;
    }
}
