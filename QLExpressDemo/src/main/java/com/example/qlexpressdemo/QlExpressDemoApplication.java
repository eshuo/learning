package com.example.qlexpressdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.qlexpressdemo.mapper")
public class QlExpressDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QlExpressDemoApplication.class, args);
    }

}
