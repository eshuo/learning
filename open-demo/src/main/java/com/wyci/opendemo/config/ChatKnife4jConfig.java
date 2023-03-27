package com.wyci.opendemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ChatKnife4jConfig {
    @Bean(value = "chatApiDoc")
    public Docket chatApiDoc() {
        String groupName="ChatGPT服务";
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("ChatGPT-API")
                .description("ChatGPT服务接口文档")
                .termsOfServiceUrl("http://localhost:8080/doc.html")
                .version("@latest")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .host("http://localhost:8080/")
                .apiInfo(apiInfo)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lucy.chat"))
                .paths(PathSelectors.any())
                .build();
    }
}
