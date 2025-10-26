package com.project.base.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

public class ProductRequestDTO {

    @NotEmpty(message = "Nome do produto não pode ser vazio")
    private String nome;

    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
    private Double preco;

    // Getters e Setters
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
