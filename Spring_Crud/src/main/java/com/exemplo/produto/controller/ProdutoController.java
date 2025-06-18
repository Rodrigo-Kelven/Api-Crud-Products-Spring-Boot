package com.exemplo.produto.controller;

import com.exemplo.produto.entity.Produto;
import com.exemplo.produto.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/produtos")
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
    public List<Produto> listar() {
        return repository.findAll();
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
    public Produto criar(
    		@Parameter(description = "Dados do Produto", required = true)
    		@RequestBody Produto produto ) {
        return repository.save(produto);
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
    public ResponseEntity<Produto> buscar(
    		@Parameter(description = "ID do Produto a ser procurado", required = true)
    		@PathVariable(required = true) Long id ) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    // metodo PUT onde á passagem de parametros tanto pelo caminho/path do endpoint, tando pelo body da requisaicao
    @PutMapping("/update/{id}")
    @Operation(summary = "Atualiza Produto pelo ID", description = "Endpoint para realizar atualização do Produto pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Produto atualizado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto nao encontrado")
    })
    public ResponseEntity<Produto> atualizar(
    		@Parameter(description = "ID do Produto a ser atualizado.", required = true) 
    		@PathVariable(required = true) Long id,
    		@RequestBody Produto produtoAtualizado ) {
        return repository.findById(id)// pega o produto pelo id no banco de dados
        		// aqui é uma funcao lambda, onde dentro dela tem o objeto produto que foi pego pelo id
        		// o objeto que foi encontrdo pelo id é passado para a funcao lambda e seus metodos chamam/recebem os parametros passados pelo corpo da requisicao
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());
                    // caso ocorra tudo ok, ReponseEntity.ok e salva o objeto com novos dados setados pela variavel que possui a injecao de dependencias  para realizar o "commit"
                    return ResponseEntity.ok(repository.save(produto));
                })
                // caso de errado a busca, o ResponseEntity chama uma classe notFound
                .orElse(ResponseEntity.notFound().build());
    }
    
    
    // endpoint Delete que recebe parametros pelo caminho/path do endpoint
    // ResponseEntity por ser uma resposta HTTP,status e corpo, retorna void, somente o status code ser retornado
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar Produto pelo ID", description = "Enpoint para deletar produto pelo ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Produto deletado com Sucesso."),
    		@ApiResponse(responseCode = "404", description = "Produto não encontrado.")
    })
    public ResponseEntity<Void> deletar(
    		@Parameter(description = "ID do produto a ser deletado", required = true)
    		@PathVariable Long id ) {
    	//caso o id exista, a variavel com injecao de dependencia irá fazer a busca pelo id com a classe existsById()
    	// e apos isso irá deletar pelo id fornecido
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        // caso de errado a busca, o ResponseEntity chama uma classe notFound
        return ResponseEntity.notFound().build();
    }
}
