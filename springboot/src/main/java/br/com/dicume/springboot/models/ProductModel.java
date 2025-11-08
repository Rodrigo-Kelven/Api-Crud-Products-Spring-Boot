package br.com.dicume.springboot.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
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
	private BigDecimal price;
	private String category;
	private String description;
	private String supplier;
	
	// Construtor que recebe o DTO
    public ProductModel(String name, BigDecimal price, String descripton, String supplier, String category) {
        this.name = name;
        this.price = price;
        this.description = descripton;
        this.supplier = supplier;
        this.category = category;
    }

    // Método para converter DTO para modelo
    public static ProductModel fromDto(ProductRecordDto dto) {
        return new ProductModel(dto.name(), dto.price(), dto.description(), dto.supplier(), dto.category());
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescripton(String description) {
		this.description = description;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
