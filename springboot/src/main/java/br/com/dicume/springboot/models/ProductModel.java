package br.com.dicume.springboot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import br.com.dicume.springboot.dtos.ProductRecordDto;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
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
	public void setId(UUID id) {
		this.id = id;
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
