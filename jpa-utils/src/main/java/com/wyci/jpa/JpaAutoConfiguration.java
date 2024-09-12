package com.wyci.jpa;

import com.wyci.jpa.config.JpaConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-09-12 14:43
 * @Version V1.0
 */
@Configuration
@EnableAutoConfiguration
@Import(JpaConfig.class)
public class JpaAutoConfiguration {

}
