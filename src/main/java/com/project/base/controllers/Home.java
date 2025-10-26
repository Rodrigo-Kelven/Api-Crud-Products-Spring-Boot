package com.project.base.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2")
@Tag(name = "API Home", description = "Endpoint home of API")
public class Home {
	

    @GetMapping("/saudacao")
    @Operation(
			summary = "Endpoint saudação",
			description = "Endpoint of pagina home of API, status of API"			
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tudo OK, retornado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro aparente."),
			@ApiResponse(responseCode = "303", description = "Redirecionamento.")
	})
    public String saudacao() {
        return "Olá, mundo!";
    }
    
    @GetMapping("/hello")
    @Operation(
			summary = "Endpoint home",
			description = "Endpoint home of API, status of API"			
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tudo OK, retornado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro aparente."),
			@ApiResponse(responseCode = "303", description = "Redirecionamento.")
	})
    public String home() {
    	return "Ola, seja bem vindo a API em Spring Boot. ;)";
    }
}


