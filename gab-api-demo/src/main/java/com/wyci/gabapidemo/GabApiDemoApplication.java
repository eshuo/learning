package com.wyci.gabapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.wyci")
public class GabApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GabApiDemoApplication.class, args);
    }

}
