package com.exemplo.produto.service;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemplo.produto.entity.Produto;
import com.exemplo.produto.repository.ProdutoRepository;



@Service
public class ProdutoService {

	
	// injecao de dependencias
    private final ProdutoRepository repository;
    
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }
    
    
    // service para listar produtos
    public ResponseEntity<List<Produto>> listarProdutosService() {
        long count = repository.count();
        
        if (count == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Message", "Nenhum produto encontrado!")
                .build();
        }

        // Cria os cabeçalhos personalizados
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Produtos Retornados.");  // Corrigido o nome do cabeçalho

        List<Produto> produtos = repository.findAll();
        return ResponseEntity.ok()
            .headers(headers)
            .body(produtos);
    }
    
    
    // service para criar produto
    // retorna uma resposta de corpo com cabeçalho http com o objeto de tipo produto
    public ResponseEntity<Produto> createProdutoService(Produto produto){
    	// injecao de dependencia instanciada la encima
    	Produto produtoCreate = repository.save(produto);
        return ResponseEntity.created(null)
        			.header("Message", "Produto criado com sucesso!")
        			.body(produtoCreate);
    }
    
    
    // service para buscar produto por ID
    public ResponseEntity<Produto> searchProdutoById(Long id){
    	Long count = repository.count();
    	
    	if (count == 0) {
    		return ResponseEntity.noContent()
    				.header("Message", "Nenhum produto existente!")
    				.build();
    	}
    	
        return repository.findById(id)
                .map(produto -> ResponseEntity.ok()
                		.header("Message", "Produto encontrado!")
                		.body(produto))
                .orElse(ResponseEntity.notFound()
                		.header("Message", "Produto não encontrado!")
                		.build());
    }
    
    
    // servico para atualizar produto, recebe o ID e o RequestBody dos dados do produto atualizado
    public ResponseEntity<Produto> updateProdutoService(Long id, Produto produtoAtualizado){
    	
    	return repository.findById(id)// pega o produto pelo id no banco de dados
        		// aqui é uma funcao lambda, onde dentro dela tem o objeto produto que foi pego pelo id
        		// o objeto que foi encontrdo pelo id é passado para a funcao lambda e seus metodos chamam/recebem os parametros passados pelo corpo da requisicao
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());
                    
                    // caso ocorra tudo ok, ReponseEntity.ok e salva o objeto com novos dados setados pela variavel que possui a injecao de dependencias  para realizar o "commit"
                    Produto produtoSalvo = repository.save(produto);
                    
                    return ResponseEntity.ok()
                    		.header("Message", "Produto atualizado com sucesso!")
                    		// o metodo .body() serve para o retorno de resposa com corpo na resposa do cabeçalho HTTP
                    		// exemplo status code: 201
                    		.body(produtoSalvo);
                })
                // caso de errado a busca, o ResponseEntity chama uma classe notFound
                .orElse(ResponseEntity.notFound()
                		.header("Message", "Produto não encontrado.")
                		// ja o metodo .build() serve para resposta que nao precisem de corpo, somente o cabecalho HTTP
                		// exemplo status code: 204, 404
                		.build());
    	
    }
    
    
    public ResponseEntity<Void> deletarProdutoById(Long id){
    
    	//caso o id exista, a variavel com injecao de dependencia irá fazer a busca pelo id com a classe existsById()
    	// e apos isso irá deletar pelo id fornecido
        if (repository.existsById(id)) {
            repository.deleteById(id);
         // noContent() significa o status Code 204, exclusao, se o id existir deleta e retorna o status Code 204 para informar que a exclusao foi bem suscedida
            return ResponseEntity.noContent()
            		.header("Message", "Produto deletado com sucesso!")
            		.build();
        }
        
        long count = repository.count();
        if (count == 0) {
            // Caso não haja produtos, retorna 404 Not Found com uma mensagem
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .header("Message", "Nenhum produto encontrado para exclusão.")
                                 .build();
        }
        
        // caso de errado a busca, o ResponseEntity chama uma classe notFound(), que retorna o erro 404
        return ResponseEntity.notFound()
        		.header("Message", "Produto não encontrado!")
        		.build();
    	
    }
    
    public ResponseEntity<Void> deleteProdutoAll(){
    	
    	 // Verifique se existem produtos na base de dados
        long count = repository.count();
        if (count == 0) {
            // Caso não haja produtos, retorna 404 Not Found com uma mensagem
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .header("Message", "Nenhum produto encontrado para exclusão.")
                                 .build();
        }
        
        // Caso haja produtos, deleta todos
        repository.deleteAll();
        return ResponseEntity.noContent()
        		.header("Message", "Produtos deletados com sucesso!")
        		.build();  // Retorna 204 No Content quando a exclusão for bem-sucedida
    	
    }
    
    
    
    
}
