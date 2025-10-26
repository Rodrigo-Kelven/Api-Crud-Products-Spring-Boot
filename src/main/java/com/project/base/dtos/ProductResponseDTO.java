package com.project.base.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductResponseDTO {

    private UUID id;
    private String nome;
    private Double preco;
    private LocalDateTime dataHora;

    // Getters e Setters
    public UUID getId() {
        return id;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
