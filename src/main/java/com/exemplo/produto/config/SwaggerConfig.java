package com.exemplo.produto.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Produtos",
        description = "API para gerenciar produtos",
        version = "0.0.3"
    )
)
public class SwaggerConfig {

    // Configuração do grupo de produtos
    @Bean
    public GroupedOpenApi produtoApi() {
        return GroupedOpenApi.builder()
                .group("produtos")
                .pathsToMatch("/produtos/**")
                .build();
    }
    
    @Bean
    public GroupedOpenApi HelloApi() {
        return GroupedOpenApi.builder()
                .group("Hello")
                .pathsToMatch("/**")
                .build();
    }

    
}