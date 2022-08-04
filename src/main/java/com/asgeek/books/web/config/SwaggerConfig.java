package com.asgeek.books.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("SpringBook API")
                        .description("Spring book application")
                        .version("v0.0.1"));
    }
}
