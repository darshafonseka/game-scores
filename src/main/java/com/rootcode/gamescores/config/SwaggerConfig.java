package com.rootcode.gamescores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springSwaggerExample() {
        // Can be accessed through http://localhost:8080/swagger-ui/index.html
        return new OpenAPI()
                .info(new Info().title("Gaming Platform")
                        .description("Gaming Platform APIs")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
