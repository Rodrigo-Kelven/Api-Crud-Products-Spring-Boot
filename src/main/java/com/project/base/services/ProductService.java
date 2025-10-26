package com.project.base.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.base.dtos.ProductRequestDTO;
import com.project.base.models.Product;
import com.project.base.repository.ProdutoRepository;

@Service
public class ProductService {

    // Injeção de dependências
    private final ProdutoRepository repository;

    public ProductService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Serviço para listar produtos
    public ResponseEntity<List<Product>> listarProdutosService() {
        List<Product> produtos = repository.findAll();

        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Message", "Nenhum produto encontrado!")
                    .build();
        }

        // Cria os cabeçalhos personalizados
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Produtos retornados.");

        return ResponseEntity.ok()
                .headers(headers)
                .body(produtos);
    }

    // Serviço para criar produto
    public ResponseEntity<Product> createProdutoService(ProductRequestDTO productRequestDTO) {

        // Convertendo o DTO para a entidade Product
        Product produto = new Product();
        produto.setNome(productRequestDTO.getNome());
        produto.setPreco(productRequestDTO.getPreco());
        produto.setCreatedAt(LocalDateTime.now()); // Definindo a data de criação

        // Persistindo o produto
        Product produtoCriado = repository.save(produto);

        // Retornando a resposta com o código 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED) // A URL da nova criação pode ser colocada aqui, se desejado
                .header("Message", "Produto criado com sucesso!") // Cabeçalho customizado
                .body(produtoCriado); // Corpo da resposta com o produto criado
    }


    // Serviço para buscar produto por ID
    public ResponseEntity<Product> searchProdutoById(UUID id) {
        return repository.findById(id)
                .map(produto -> ResponseEntity.ok()
                        .header("Message", "Produto encontrado!")
                        .body(produto))
                .orElse(ResponseEntity.notFound()
                        .header("Message", "Produto não encontrado!")
                        .build());
    }

    // Serviço para atualizar produto
    public ResponseEntity<Product> updateProdutoService(UUID id, ProductRequestDTO produtoAtualizado) {
        return repository.findById(id)
                .map(produto -> {
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setPreco(produtoAtualizado.getPreco());

                    // Salva o produto com os dados atualizados
                    Product produtoSalvo = repository.save(produto);
                    return ResponseEntity.ok()
                            .header("Message", "Produto atualizado com sucesso!")
                            .body(produtoSalvo);
                })
                .orElse(ResponseEntity.notFound()
                        .header("Message", "Produto não encontrado.")
                        .build());
    }

    // Serviço para deletar produto por ID
    public ResponseEntity<Void> deletarProdutoById(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
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

    // Serviço para deletar todos os produtos
    public ResponseEntity<Void> deleteProdutoAll() {
        long count = repository.count();
        if (count == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Message", "Nenhum produto encontrado para exclusão.")
                    .build();
        }

        repository.deleteAll();
        return ResponseEntity.noContent()
                .header("Message", "Produtos deletados com sucesso!")
                .build();
    }
}
