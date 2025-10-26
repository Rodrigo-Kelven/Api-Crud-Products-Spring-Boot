package com.project.base.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SuppressWarnings("unused")
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API Management Products",
        description = "API para gerenciar produtos",
        version = "0.0.5"
    )
)
public class SwaggerConfig {
	// sem config pois esta dando muito porblema!!
	// configraçoes sao individuais por endpoints
}
