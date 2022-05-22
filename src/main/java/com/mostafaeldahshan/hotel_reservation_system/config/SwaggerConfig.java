package com.mostafaeldahshan.hotel_reservation_system.config;

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
public class SwaggerConfig {

    @Bean
    public Docket postsApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mostafaeldahshan.hotel_reservation_system"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title("Hotel Reservations System")
                .description("Backend API for managing Hotel Reservations using Spring Boot")
                .contact(new Contact("Mostafa ElDahshan",
                        "www.github.com/MostafaAbdelkarim",
                        "mostafaabdelkarim22@gmail.com"))
                .license("MIT LICENSE")
                .version("0.0.1")
                .build();
    }
}
