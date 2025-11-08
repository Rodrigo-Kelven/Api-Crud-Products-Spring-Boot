package br.com.dicume.springboot.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	// Configuração global para todas as rotas
        registry.addMapping("/**") // Permite CORS para todas as rotas
                .allowedOrigins("http://127.0.0.1") // Permite apenas este domínio -> LOCALMENTE
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true) // Permite credenciais (cookies, tokens, etc.)
                .maxAge(3600); // O tempo máximo para o cache das permissões CORS
        
     // Configuração CORS específica para o endpoint de login
        registry.addMapping("/login") // Rota específica de login
                .allowedOrigins("http://login.example.com") // Permite apenas o domínio de login
                .allowedMethods("POST") // Permite apenas o método POST para login
                .allowedHeaders("*") // Permite todos os headers
                .allowCredentials(true) // Permite credenciais (cookies, tokens, etc.)
                .maxAge(3600); // Tempo máximo para o cache das permissões CORS
        
    }
}
