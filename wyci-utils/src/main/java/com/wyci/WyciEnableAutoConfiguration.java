package com.wyci;

import com.wyci.config.LogbookConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-08-06 17:11
 * @Version V1.0
 */
@Configuration
@Import(LogbookConfiguration.class)
public class WyciEnableAutoConfiguration {

}
