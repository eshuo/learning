package com.wyci.webdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wyci")
public class WebDemoApplication {




    public static void main(String[] args) {
        SpringApplication.run(WebDemoApplication.class, args);
    }

}
