package com.green.greengram3.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components())
                .info(new Info().title("GreenGram Season3")
                        .description("인스타그램 클론코딩")
                        .version("2.0.0")
                );
    }
}
