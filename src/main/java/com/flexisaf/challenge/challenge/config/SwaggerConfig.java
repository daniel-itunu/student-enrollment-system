package com.flexisaf.challenge.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.technologies"))
                .build()
                .apiInfo(demoApiDetails());
    }

    private ApiInfo demoApiDetails() {
        return new ApiInfo(
                "demo Api",
                "demo",
                "1.0",
                "demo",
                new springfox.documentation.service.Contact("demo", "demo.com", "info@demo.com"),
                "Api Licence",
                "https://demo.com/",
                Collections.emptyList()
        );
    }
}

