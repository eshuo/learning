package com.example.redisdemo.config.thread;

import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-01-20 14:06
 * @Version V1.0
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {


    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolConfig.class);




    @Bean("asyncAuthMqTask")
    public TaskExecutor asyncLogTask() {
        return getThreadPoolTaskExecutor("asyncAuthMqTask_");
    }


    @Bean("asyncAuthTask")
    public TaskExecutor asyncAuthTask() {
        return getThreadPoolTaskExecutor("asyncAuthTask_");
    }

    @Bean("callerRunsAsyncTask")
    public TaskExecutor callerRunsAsyncTask() {
        ThreadPoolTaskExecutor executor = getApiThreadPoolTaskExecutor("callerRunsAsync_");
        // 设置拒绝策略 当队列满了 且达到最大线程时，抛出异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 等待所有任务结束后再关闭线程池
        return executor;
    }


    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor(String name) {
        ThreadPoolTaskExecutor executor = getApiThreadPoolTaskExecutor(name);
        // 设置拒绝策略
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setRejectedExecutionHandler(new MyCallerRunsPolicy(callerRunsAsyncTask(), name));
        return executor;
    }

    private ThreadPoolTaskExecutor getApiThreadPoolTaskExecutor(String name) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(10);
        // 设置最大线程数
        executor.setMaxPoolSize(20);
        // 设置队列容量
        executor.setQueueCapacity(100);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix(name);
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}
