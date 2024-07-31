//package com.eetrust.webdemo.demos.init;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Properties;
//import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//
///**
// * @Description
// * @Author wangshuo
// * @Date 2024-07-16 10:34
// * @Version V1.0
// */
//@Component
//public class InitConfig implements ApplicationListener<ServletWebServerInitializedEvent> {
//
////    @Autowired
////    private ServletWebServerApplicationContext servletWebServerApplicationContext;
//
//    @Override
//    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
//        //获取运行端口
//        System.err.println("InitConfig init, port: " + event.getWebServer().getPort());
//        //获取运行IP
//        try {
//            final InetAddress localHost = InetAddress.getLocalHost();
////            final String hostAddress = localHost.getHostAddress();
//            System.err.println("hostName: " + InetAddress.getLocalHost().getHostName());
//            System.err.println("localhost: " + InetAddress.getLocalHost().getHostAddress());
//        } catch (UnknownHostException e) {
//            throw new RuntimeException(e);
//        }
//        // 获取 git.properties
//        final Properties gitProperties = getGitProperties();
//
//        gitProperties.forEach((k, v) -> System.err.println(k + " : " + v));
//
//
//    }
//
//
//    private Properties getGitProperties() {
//        Properties properties = new Properties();
//        ClassLoader classLoader = getClass().getClassLoader();
//        try (InputStream inputStream = classLoader.getResourceAsStream("git.properties")) {
//            properties.load(inputStream);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return properties;
//    }
//}
