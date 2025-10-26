package com.project.base.models;


import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO para gerar UUID automaticamente
    private UUID id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = false, nullable = false)
    private String nome;
    
    @Column(unique = false, nullable = false)
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que 0")
    private Double preco;
    
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_at")

    private LocalDateTime createdAt;

    @Version
    @Column(name = "version")
    private Integer version;
    

    
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}