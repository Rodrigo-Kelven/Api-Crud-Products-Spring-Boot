package com.exemplo.produto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.produto.entity.Produto;
import com.exemplo.produto.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produtos")
@Tag(name = "API Produto", description = "Operações relacionadas aos produtos.")
public class ProdutoController {


	// injecao de denpendencias de service
    private final ProdutoService service;
    
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    
    
    
    // anotacao para dizer que este endpoint é de tipo GET com o path /list
    // como em java tudo sao classes e funcoes, esta funcao retorna os dados cadastrados, como esta funcao retorna algo, precisa de um tipo de parametro de retorno
    // logo usa-se a interface List<> com o tipo de parametro Produto -> List<Produto> 
    // como foi injetado dependencias na variavel repository, o JPARepository realiza tudo de forma automatica, pesquisando, criado, atualizando e deletando
    @GetMapping("/list")
    @Operation(
    		summary = "Retorna todos os produtos", 
    		description = "Endpoint retorna todos os produtos do Banco de dados."
    		)
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Retornado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Nenhum Produto enconteado.")
    })
    public ResponseEntity<List<Produto>> listarProdutoController() {
    	
    	return service.listarProdutosService();
    
    }
    
    
    // anotacao para dizer que este endpoint é de tipo POST com o path /create
    // de tipo de retorno Produto, a funcao recebe parametros, logo, o Spring disponibiliza uma anotacao @RequestBody, que significa que irá receber parametros do body,
    // parametros esses de tipo Produto, onde a variavel produto recebe e realiza o save() que de forma automatica o spring com o JPARepository cuida disso
    @PostMapping("/create")
    @Operation(
    		summary = "Criar Produtos", 
    		description = "Endpoint para a criação de Produtos."
    		)
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto criado com Sucesso."),
    		@ApiResponse(responseCode = "400", description = "Erro ao criar produto.")
    })
    public ResponseEntity<Produto> criarProdutoController(
    		@Parameter(description = "Dados do Produto", required = true)
    		@RequestBody Produto produto ) {
	        return service.createProdutoService(produto);
    }


    // anotacao para dizer que este endpoint é de tipo GET com o path /search/{id}
    // a anotacao PathVariable diz que um parametro virá do path->caminho/url, este parametro é de tipo Lonf com o nome de id
    // ResponseEntity é uma classe do Spring que representa toda a resposta HTTP, incluindo status, cabeçalhos e corpo.
    // o repository se reposnsabiliza por buscar o objeto que corresponde ao parametro id que foi passado
    // .map(ResponseEntity::ok) significa que, se o Optional contém um produto, ele será transformado em um ResponseEntity com status 200 e o produto no corpo. 
    // ResponseEntity.notFound().build() é uma forma de criar uma resposta HTTP com status 404 Not Found usando a classe ResponseEntity
    // ResponseEntity.notFound() é um método estático que retorna um construtor (builder) para uma resposta HTTP com status 404.
    @GetMapping("/search/{id}")
    @Operation(
    		summary = "Busca Produto pelo ID", 
    		description = "Endpoint para busca de Produtos pelo ID"
    		)
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto encontrado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ResponseEntity<Produto> buscarProdutoByIdController(
    		@Parameter(description = "ID do Produto a ser procurado", required = true)
    		@PathVariable Long id
    		) {
    	
    	return service.searchProdutoById(id);
    }

    
    // metodo PUT onde á passagem de parametros tanto pelo caminho/path do endpoint, tando pelo body da requisaicao
    @PutMapping("/update/{id}")
    @Operation(
    		summary = "Atualiza Produto pelo ID",
    		description = "Endpoint para realizar atualização do Produto pelo ID"
    		)
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto atualizado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    })
    public ResponseEntity<Produto> atualizarProdutoByIdController(
    		@Parameter(description = "ID do Produto a ser atualizado.", required = true) 
    		@PathVariable(required = true) Long id,
    		@RequestBody Produto produtoAtualizado ) {
    	
    	return service.updateProdutoService(id, produtoAtualizado);
        
    	}
    
    // endpoint Delete que recebe parametros pelo caminho/path do endpoint
    // ResponseEntity por ser uma resposta HTTP,status e corpo, retorna void, somente o status code ser retornado
    @DeleteMapping("/delete/{id}")
    @Operation(
    		summary = "Deletar Produto pelo ID",
    		description = "Enpoint para deletar produto pelo ID"
    		)
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Produto deletado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ResponseEntity<Void> deletarProdutoByIdController(
    		@Parameter(description = "ID do produto a ser deletado", required = true)
    		@PathVariable Long id ) {
    	
    	return service.deletarProdutoById(id);
    	
    }

    
    @DeleteMapping("/delete")
    @Operation(
    		summary = "Deletar todos os Produtos",
    		description = "Endpoint para deletar todos os Produtos"
    		)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produtos apagados com Sucesso."),
        @ApiResponse(responseCode = "404", description = "Nenhum Produto encontrado.")
    })
    public ResponseEntity<Void> deletarProdutosAllController() {
       
    	return service.deleteProdutoAll();
    	
    }

    
    
    
}
