
package com.exemplo.produto.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;



@RestController
@RequestMapping("/")
@Tag(name = "Hello", description = "Endpoints API Hello")
public class HelloController {

	@GetMapping
	@Operation(summary = "Endpoint home", description = "Endpoint de pagina home da api, status of api")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Tudo OK, retornado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro aparente."),
			@ApiResponse(responseCode = "303", description = "Redirecionamento.")
	})
	@Async
	public CompletableFuture<ResponseEntity<String>> HelloPage() {
		return CompletableFuture.completedFuture(
				ResponseEntity.ok()
				.header("Message", "Sucess!")
				.body("Hellor Word"));
		
	}
	
	// com este tipo de configuracao na anotacao de tipo de endpoint, podemos dizer ao spring que este endpoint atente a estes dois caminhos
	// @GetMapping({"/{name}", "/"})
	// nao recomendo, tira o principio de cada endpoint ter uma unica responsabilidade, e pode se tornar mais complexo de manter
	@GetMapping("/ster-egg/{name}")
	@Operation(summary = "Saúda o usuário pelo nome", description = "Retorna uma mensagem de saudação personalizada para o nome informado.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Saudação retornada com sucesso"),
	    @ApiResponse(responseCode = "400", description = "Parâmetro inválido")
	})
	@Async
	public CompletableFuture<ResponseEntity<String>> helloPageDois(
	    @Parameter(description = "Nome do usuário para a saudação", required = true)
	    @PathVariable(required = true) String name) {
		
		return CompletableFuture.completedFuture(
				ResponseEntity.ok()
				.header("Message", "Sucess!")
				.body("Hello " + name + ", parabens voce descobriu um segredo!!"));

	}


	
}
