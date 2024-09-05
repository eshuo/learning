package com.wyci.webdemo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.IOUtils;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-07-16 10:00
 * @Version V1.0
 */
public class WebDemo {

    public static final ObjectMapper MAPPER = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static void main(String[] args) {

        //获取jar版本
        Properties properties = new Properties();
        try {
            properties.load(WebDemo.class.getResourceAsStream("/META-INF/maven/com.wyci/web-demo/pom.properties"));
        } catch (Exception e) {
        }
        String version = properties.getProperty("version");
        System.err.println("properties：" + properties);
        System.err.println("version：" + version);

        final String path = WebDemo.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.err.println("path：" + path);



    }

    private Map<String, String> readGitProperties() {
        InputStream inputStream = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            inputStream = classLoader.getResourceAsStream("git.properties");
            // 读取文件内容，自定义一个方法实现即可

            String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());

            final Map<String, String> hashMap = MAPPER.readValue(text, HashMap.class);
            return hashMap;
        } catch (Exception e) {
            System.err.println("get git version info fail" + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                System.err.println("close inputstream fail" + e);
            }
        }
        return new HashMap<>();
    }

}
