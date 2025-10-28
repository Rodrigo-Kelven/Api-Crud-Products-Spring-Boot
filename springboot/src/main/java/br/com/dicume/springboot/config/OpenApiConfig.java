package br.com.dicume.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Dicume")
                .version("0.1.6")
                .description("Esta é a documentação da API Dicume.")
                .termsOfService("https://github.com/Rodrigo-Kelven/Api-Spring-Boot")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .name("Suporte API")
                    .url("https://github.com/Rodrigo-Kelven/Api-Spring-Boot")
                    .email("https://github.com/Rodrigo-Kelven/Api-Spring-Boot"))
            );
    }
}