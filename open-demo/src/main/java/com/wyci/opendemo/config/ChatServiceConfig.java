package com.wyci.opendemo.config;

import com.wyci.opendemo.service.ChatAuthService;
import com.wyci.opendemo.service.impl.DefaultChatAuthServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author kindear
 * chat服务配置信息
 */
@SpringBootConfiguration
public class ChatServiceConfig {
    // Chat 鉴权服务
    @Bean
    @ConditionalOnMissingBean(value = ChatAuthService.class)
    public ChatAuthService chatAuthService(){
        return new DefaultChatAuthServiceImpl();
    }
}
