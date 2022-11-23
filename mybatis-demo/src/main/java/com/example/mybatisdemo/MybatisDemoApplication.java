package com.example.mybatisdemo;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.example.mybatisdemo.mapper")
public class MybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }


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
        MybatisSqlSessionFactoryBean b1 = new MybatisSqlSessionFactoryBean();
//        System.out.println("dataSourceLyz"+dataSource.toString());
        b1.setDataSource(dataSource);
        b1.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
        return b1.getObject();
    }
}
