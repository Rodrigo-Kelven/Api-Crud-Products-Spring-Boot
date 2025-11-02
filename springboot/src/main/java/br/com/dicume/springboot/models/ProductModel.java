package br.com.dicume.springboot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import br.com.dicume.springboot.dtos.ProductRecordDto;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable{ 
	// Serializable é uma interface que indica que a classe ProductModel que é uma tabela, pode ser serializada.  
	private static final long serialVersionUID = 1L; // ID de controle da JVM para as serializacoes conforma nescessários.
	/*
	 * A estratégia GenerationType.AUTO faz com que o provedor de persistência (como Hibernate) escolha automaticamente 
	 * a melhor estratégia de geração de chave primária com base no banco de dados em uso
	*/
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;
	private BigDecimal value;
	
	// Construtor que recebe o DTO
    public ProductModel(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    // Método para converter DTO para modelo
    public static ProductModel fromDto(ProductRecordDto dto) {
        return new ProductModel(dto.name(), dto.value());
    }
    
 // Construtor vazio para JPA
    public ProductModel() {}

	
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
