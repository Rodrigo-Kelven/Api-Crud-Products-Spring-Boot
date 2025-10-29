package br.com.dicume.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // Carrega o contexto completo da aplicação
@AutoConfigureMockMvc  // Configura o MockMvc automaticamente
public class SpringbootApplicationTest {

    @Autowired
    private MockMvc mockMvc;  // Injeta o MockMvc

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/"))  // Faz requisição GET para "/"
               .andExpect(status().isOk())  // Espera status HTTP 200
               .andExpect(content().string("Olá, mundo!"));  // Espera o retorno "Olá, mundo!"
    }
}
