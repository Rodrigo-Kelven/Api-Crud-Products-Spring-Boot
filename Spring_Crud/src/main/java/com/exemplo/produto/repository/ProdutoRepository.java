package com.exemplo.produto.repository;

import com.exemplo.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


// aqui basicamente a interface ProdutoRepository esta fazendo um extends de JpaRepository para receber metodos para realizar o crud
// A interface ProdutoRepository está estendendo JpaRepository para herdar métodos prontos de CRUD (como save, findById, findAll, deleteById, etc).
// JpaRepository é uma interface genérica do Spring Data JPA, por isso exige dois parâmetros:
// 1. Produto – a entidade (classe anotada com @Entity) que representa a tabela no banco de dados.
// 2. Long – o tipo do identificador (ID) da entidade Produto, ou seja, o tipo da chave primária.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
