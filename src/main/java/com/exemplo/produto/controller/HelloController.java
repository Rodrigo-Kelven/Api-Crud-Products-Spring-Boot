
package com.exemplo.produto.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
//@Tag(name = "API Produtos Hello", description = "Endpoint de verificação de estado da api.")
public class HelloController {

	@GetMapping("/")
	@Operation(
			summary = "Endpoint home",
			description = "Endpoint de pagina home da api, status of api",
			tags = {"Status API"}
			)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tudo OK, retornado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro aparente."),
			@ApiResponse(responseCode = "303", description = "Redirecionamento.")
	})
	public ResponseEntity<String> HelloPage() {
		String message = "Hellor Word";
		
		return ResponseEntity.ok().header("message", message).body(message);
	}
	
	
	
	@GetMapping("/hello/{name}")
	@Operation(
			summary = "Saúda o usuário pelo nome",
			description = "Retorna uma mensagem de saudação personalizada para o nome informado.",
			tags = {"API Saudacoes"}
			)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Saudação retornada com sucesso"),
	    @ApiResponse(responseCode = "400", description = "Parâmetro inválido")
	})
	public ResponseEntity<String> helloPageDois(
	    @Parameter(description = "Nome do usuário para a saudação", required = true)
	    @PathVariable(required = true) String name) {
		
		return ResponseEntity.ok("Hello " + name);

	}
	
	

	
}
