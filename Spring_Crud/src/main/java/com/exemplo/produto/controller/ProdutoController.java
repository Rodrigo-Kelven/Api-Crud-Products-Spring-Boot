package com.exemplo.produto.controller;

import com.exemplo.produto.entity.Produto;
import com.exemplo.produto.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api-v1/produtos")
@Tag(name = "Products", description = "Endpoints API Products")
public class ProdutoController {

    // variavel que recebe as dependencias JPA injetadas em ProdutoRepository
    private final ProdutoRepository repository;

    // Criando uma “conexão lógica” com o banco de dados
    // Aqui, a variável repository é a referência do objeto injetado pelo Spring,
    // que sabe como acessar o banco por trás dos panos.
    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }
    
    

    // anotacao para dizer que este endpoint é de tipo GET com o path /list
    // como em java tudo sao classes e funcoes, esta funcao retorna os dados cadastrados, como esta funcao retorna algo, precisa de um tipo de parametro de retorno
    // logo usa-se a interface List<> com o tipo de parametro Produto -> List<Produto> 
    // como foi injetado dependencias na variavel repository, o JPARepository realiza tudo de forma automatica, pesquisando, criado, atualizando e deletando
    @GetMapping("/list")
    @Operation(summary = "Retorna todos os produtos", description = "Endpoint retorna todos os produtos do Banco de dados.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Retornado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Nenhum Produto enconteado.")
    })
    @Async
    public CompletableFuture<ResponseEntity<List<Produto>>> listProducts() {
    	
    	// aloca numa variavel de tipo Long o valor que é referente a quantidade de objetos dentro do banco de dados
    	// armazena a quantidade de dados no banco de dados
    	Long count = repository.count();
    	
    	
    	List<Produto> productsListed = repository.findAll();
    	
    	// se nao tiver nenhum dado no banco de dados
    	if (count == 0) {
    		return CompletableFuture.completedFuture(
    				// ResponseEntity response com o not Found -> status code 404
    				// ResponseEntity tem todas as classes de resposta de cabecalho, que soa os status code.
    				ResponseEntity.notFound()
	    				.header("Message", "Nenhum Produto existente!")
	    				.build() // Conclui a construção do ResponseEntity e retorna o objeto finalizado.
    			);
    	}
    	
        return CompletableFuture.completedFuture(
        		ResponseEntity.ok()
        				.header("Message", "Produtos Listados!")
        				.body(productsListed)
        		);
    }

    // anotacao para dizer que este endpoint é de tipo POST com o path /create
    // de tipo de retorno Produto, a funcao recebe parametros, logo, o Spring disponibiliza uma anotacao @RequestBody, que significa que irá receber parametros do body,
    // parametros esses de tipo Produto, onde a variavel produto recebe e realiza o save() que de forma automatica o spring com o JPARepository cuida disso
    @PostMapping("/create")
    @Operation(summary = "Criacao de Produtos", description = "Endpoint para a criação de Produtos.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto criado com Sucesso."),
    		@ApiResponse(responseCode = "400", description = "Erro ao criar produto.")
    })
    @Async
    public CompletableFuture<ResponseEntity<Produto>> createProduct(
    		@Parameter(description = "Dados do Produto", required = true)
    		@RequestBody Produto produto ) {
    	
    	Produto productCreate = repository.save(produto);
    	
        return CompletableFuture.completedFuture(
        		ResponseEntity.created(null)
        		.header("Message", "Produto criado!")
        		.body(productCreate)
        		);
    }

    
    // anotacao para dizer que este endpoint é de tipo GET com o path /search/{id}
    // a anotacao PathVariable diz que um parametro virá do path->caminho/url, este parametro é de tipo Lonf com o nome de id
    // ResponseEntity é uma classe do Spring que representa toda a resposta HTTP, incluindo status, cabeçalhos e corpo.
    // o repository se reposnsabiliza por buscar o objeto que corresponde ao parametro id que foi passado
    // .map(ResponseEntity::ok) significa que, se o Optional contém um produto, ele será transformado em um ResponseEntity com status 200 e o produto no corpo. 
    // ResponseEntity.notFound().build() é uma forma de criar uma resposta HTTP com status 404 Not Found usando a classe ResponseEntity
    // ResponseEntity.notFound() é um método estático que retorna um construtor (builder) para uma resposta HTTP com status 404.
    @GetMapping("/search/{id}")
    @Operation(summary = "Busca Produto pelo ID", description = "Endpoint para busca de Produtos pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto encontrado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ResponseEntity<Produto> searchProductById(
    		@Parameter(description = "ID do Produto a ser procurado", required = true)
    		@PathVariable(required = true) Long id ) {
    	
    	Long count = repository.count();
    	
    	Optional<Produto> produtoOptional = repository.findById(id);

    	if (count == 0) {
    		return ResponseEntity.notFound()
    				.header("Message", "Nenhum Produto existente!")
    				.build();
    	}

    	
    	return produtoOptional
                .map(produto -> ResponseEntity.ok()
                        .header("Message", "Produto encontrado!")
                        .body(produto))
                .orElse(ResponseEntity.notFound()
                		.header("Message", "Produto não encontrado!")
                		.build());
    }

    
    // metodo PUT onde á passagem de parametros tanto pelo caminho/path do endpoint, tando pelo body da requisaicao
    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza Produto pelo ID", description = "Endpoint para realizar atualização do Produto pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto atualizado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    })
    @Async
    public CompletableFuture<ResponseEntity<Produto>> updateProductById(
            @Parameter(description = "ID do Produto a ser atualizado.", required = true) 
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado) {

        // Contagem de produtos (poderia ser útil, mas vamos assumir que não impacta na operação de atualização)
        Long count = repository.count();

        // Caso não haja produtos no banco de dados, retornamos um "not found"
        if (count == 0) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.notFound()
                            .header("Message", "Nenhum Produto existente!")
                            .build()
            );
        }

        // Buscar produto pelo id de forma assíncrona
        return CompletableFuture.supplyAsync(() -> 
            repository.findById(id) // Pega o produto pelo ID no banco de dados
                .map(produto -> {
                    // Atualiza os campos do produto com os dados fornecidos na requisição
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());

                    // Salva o produto atualizado
                    Produto produtoSalvo = repository.save(produto);

                    // Retorna a resposta com o status 201 (Criado) e o produto atualizado
                    return ResponseEntity.created(null)
                            .header("Message", "Produto atualizado com sucesso!")
                            .body(produtoSalvo);
                })
                // Se o produto não for encontrado, retornamos um "not found"
                .orElse(ResponseEntity.notFound()
                        .header("Message", "Produto não encontrado!")
                        .build())
        );
    }

    
    
    // endpoint Delete que recebe parametros pelo caminho/path do endpoint
    // ResponseEntity por ser uma resposta HTTP,status e corpo, retorna void, somente o status code ser retornado
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar Produto pelo ID", description = "Enpoint para deletar produto pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Produto deletado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    @Async
    public CompletableFuture<ResponseEntity<Void>> deleteProductById(
    		@Parameter(description = "ID do produto a ser deletado", required = true)
    		@PathVariable Long id ) {
    	//caso o id exista, a variavel com injecao de dependencia irá fazer a busca pelo id com a classe existsById()
    	// e apos isso irá deletar pelo id fornecido
    	
    	Long count = repository.count();
    	
    	if (count == 0) {
    		return CompletableFuture.completedFuture(ResponseEntity.notFound()
    				.header("Message", "Nenhum Produto para a exclusão!")
    				.build());
    	}
    	
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return CompletableFuture.completedFuture(ResponseEntity.noContent() // no Content signifca que nao precisa de retorno, status code 204, deletado com sucesso
            		.header("Message", "Produto deletado com sucesso!")
            		.build());
        }
        // caso de errado a busca, o ResponseEntity chama uma classe notFound, status code 404 -> not Found
        return CompletableFuture.completedFuture(ResponseEntity.notFound()
        		.header("Message", "Produto não encontrado!")
        		.build());
    }
    
    
    @DeleteMapping("/delete")
    @Operation(summary = "Deletar todos os Produtos", description = "Endpoint para deletar todos os Produtos.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Produtos deletados com sucesso."),
    		@ApiResponse(responseCode = "404", description = "Nenhum Produto para a exclusão!")
    })
    @Async
    public CompletableFuture<ResponseEntity<Void>> deleteAllProducts() {
        // Conta o número de produtos no banco
        Long count = repository.count();
        
        // Se não houver produtos, retorna um código de status 404 (não encontrado)
        if (count == 0) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound()
                    .header("Message", "Nenhum Produto para a exclusão!")
                    .build());
        }

        // Se houver produtos, exclui todos e retorna código 204 (sem conteúdo)
        repository.deleteAll();
        return CompletableFuture.completedFuture(ResponseEntity.noContent()
        		.header("Message", "Produtos deletados com sucesso!")
        		.build());
    }

    
    
}
