package com.dimple.framework.config;

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
 * @ClassName: SwaggerConfig
 * @Description:
 * @Auther: Owenb
 * @Date: 11/20/18 10:24
 * @Version: 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                包扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.dimple.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dimple Blog管理系统")
                .description("Dimple个人博客管理系统Swagger页面调试页面")
                //服务条款地址
                .termsOfServiceUrl("http://www.bianxiaofeng.com")
                //版本
                .version("V1.0")
                //添加联系
                .contact(new Contact("Dimple", "http://www.bianxiaofeng.com", "bianxiaofeng@sohu.com"))
                .build();
    }

}
