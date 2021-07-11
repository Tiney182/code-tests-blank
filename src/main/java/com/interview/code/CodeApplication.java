package com.interview.code;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class, args);
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title("Synalogik API")
                .description("Synalogik Technical Test").version("v1.0.0").license(null)
                .contact(new Contact().name("Andrew Martyn Brightman").url("https://github.com/Tiney182")
                        .email("veloen2@googlemail.com")));
    }
}
