package com.exemplo.produto.entity;

import jakarta.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz o banco gerar o ID automaticamente
    @Schema(description = "ID Product")
    private Long id;

    @Schema(description = "Name Product", example = "Iphone 15")
    private String nome;
    
    @Schema(description = "Price Product", example = "2400")
    private Double preco;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
