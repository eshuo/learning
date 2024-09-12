package com.wyci.jpa.config;

import com.wyci.jpa.repository.JpaDefaultRepositoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-09-12 14:23
 * @Version V1.0
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = JpaDefaultRepositoryBean.class, basePackages = {"com.**"})
public class JpaConfig {

}
