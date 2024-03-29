package com.gen.api.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-03-18
 * Time: 오후 4:29
 */
@Profile({"local", "dev"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gen.api.server"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());


    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("common page API")
                .build();

    }


}
