package com.x.demo.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Leo
 */
@EnableSwagger2
@EnableKnife4j
@Configuration
public class Knife4jConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("这是一个分组名称")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.x.demo.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("x-demo-service接口文档")
                .description("x-demo-service接口文档")
                .termsOfServiceUrl("https://www.cnblogs.com/shileibrave/")
                .contact(new Contact("Leo", "https://www.cnblogs.com/shileibrave/", "shileibrave@163.com"))
                .version("1.0")
                .build();
    }
}
