package br.com.dicume.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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


/*
    # Ednpoints of actuador:

    Health (/actuator/health): Verifica o status da aplicação e de componentes, como banco de dados.
    Metrics (/actuator/metrics): Coleta e exibe métricas de desempenho da aplicação.
    Env (/actuator/env): Exibe as propriedades de ambiente da aplicação.
    Beans (/actuator/beans): Lista os beans registrados no contexto Spring.
    Loggers (/actuator/loggers): Permite visualizar e configurar o nível de log da aplicação.
 */