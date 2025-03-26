package com.eshuo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.eshuo")
@EnableScheduling
//@EnableJpaRepositories(basePackages = "com.eshuo.repository")  // 指定扫描 Repository 的包路径
//@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.eshuo.repository")  // 确保 Repository 的扫描
@EntityScan(basePackages = "com.eshuo.dao")  // 确保实体类的扫描
public class AiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiApiApplication.class, args);
    }

}
