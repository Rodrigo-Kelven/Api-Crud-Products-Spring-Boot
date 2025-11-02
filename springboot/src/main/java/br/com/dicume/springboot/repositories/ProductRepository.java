package br.com.dicume.springboot.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dicume.springboot.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID>{
	// Método para buscar produtos dentro de um intervalo de preço usando BigDecimal
    List<ProductModel> findByPriceBetween(BigDecimal min, BigDecimal max);
}
