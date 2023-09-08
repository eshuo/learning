package com.wyci.config;

import com.wyci.utils.uuid.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apiguardian.api.API;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.CorrelationId;

import static org.apiguardian.api.API.Status.INTERNAL;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-09-08 10:07
 * @Version V1.0
 */
@Configuration
public class LogbookConfiguration {
    public static final String LOG_ID = "LOG-THREAD-ID";

    @API(status = INTERNAL)
    @Bean
    public CorrelationId correlationId() {
        return request -> {
            String header = String.valueOf(request.getHeaders().get(LOG_ID));
            if (StringUtils.isNotBlank(header) && !"null".equals(header)) {
                MDC.put(LOG_ID, header);
            } else {
                final String nextId = IdGenerator.nextId(this.getClass());
                MDC.put(LOG_ID, nextId);
            }
            return header;
        };
    }
}
