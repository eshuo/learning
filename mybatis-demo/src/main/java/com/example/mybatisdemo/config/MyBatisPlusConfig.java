package com.example.mybatisdemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-02-01 10:21
 * @Version V1.0
 */
@Configuration
public class MyBatisPlusConfig {


    /**
     * com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration#sqlSessionFactory类中的bean里面说明了创建SqlSessionFactory bean时
     * ，自定义多数据源使用 MybatisSqlSessionFactoryBean 而不是 SqlSessionFactoryBean，否则无法调用mybatis的api，会如下错误
     * org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): XXXX
     *
     * @param dataSource
     *
     * @return
     *
     * @throws Exception
     */
    @Primary
    @Bean("masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(DataSource dataSource) throws Exception {
        /**
         * 使用 mybatis plus 配置
         */
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //设置分页拦截
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.addInterceptor(paginationInterceptor());
        mybatisSqlSessionFactoryBean.setConfiguration(configuration);

        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        return mybatisSqlSessionFactoryBean.getObject();
    }

    /**
     * 分页插件 TODO 如果配置 SqlSessionFactory 需要在配置里面引入该拦截器
     *
     * @return
     */
    @Primary
    @Bean("paginationInterceptor")
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(1000L);
        paginationInnerInterceptor.setOverflow(false);
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);
        return mybatisPlusInterceptor;
    }
}
