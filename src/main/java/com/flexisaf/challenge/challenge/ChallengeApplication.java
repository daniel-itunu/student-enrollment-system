package com.flexisaf.challenge.challenge;

import com.flexisaf.challenge.challenge.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SwaggerConfig.class)})
@SpringBootApplication
public class ChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

}
