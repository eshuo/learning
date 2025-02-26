package com.eshuo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-02-26 20:53
 * @Version V1.0
 */
@Configuration
public class WebClientConfig {


    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }


}
