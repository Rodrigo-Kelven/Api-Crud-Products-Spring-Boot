package br.com.dicume.springboot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@SpringBootApplication
@RestController
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	
	@GetMapping("/")
    @Operation(summary = "Retorna uma saudação")
	@Tag(name = "Home", description = "Home of API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Saudação gerada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public String hello() {
        return "Olá, mundo!";
    }

}
