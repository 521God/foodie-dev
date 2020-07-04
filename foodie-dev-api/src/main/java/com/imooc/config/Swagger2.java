package com.imooc.config;

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

@Configuration
@EnableSwagger2
public class Swagger2 {
    //访问的路径  http：//localhost:8088/swagger-ui.html
    //Swagger核心配置 docket
    @Bean
    public Docket createRestapi(){
        //指定api文档类型为Swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("com.imooc.controller"))
                .paths(PathSelectors.any())
                .build();                   //用于定义api文档信息
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货网站api")
                .contact(new Contact("imooc","www.imooc.com","1252088713@qq.com"))

                .description("专为天天吃货网站提供的api")
                .version("1.0.1")
                .termsOfServiceUrl("http://www.imooc.com")
                .build();
    }

}
