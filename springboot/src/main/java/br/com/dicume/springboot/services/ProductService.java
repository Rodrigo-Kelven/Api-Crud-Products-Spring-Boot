package br.com.dicume.springboot.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.dicume.springboot.dtos.ProductRecordDto;
import br.com.dicume.springboot.exception.ResourceNotFoundException;
import br.com.dicume.springboot.models.ProductModel;
import br.com.dicume.springboot.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    
    /**
     * Create a new product.
     *
     * @param productRecordDto DTO containing product information.
     * @return ResponseEntity containing the created product.
     */
    public ResponseEntity<ProductModel> serviceSaveProduct(@Validated @RequestBody ProductRecordDto productRecordDto) {
        // Convert DTO to Model
        ProductModel productModel = ProductModel.fromDto(productRecordDto);

        // Save product in the database (ID will be auto-generated)
        ProductModel savedProduct = productRepository.save(productModel);

        // Return the saved product with UUID
        return ResponseEntity.status(HttpStatus.CREATED)
        		.header("X-Custom-Header", "Produto cadastrado com sucesso!")
                .body(savedProduct);
    }
    
    
    /**
     * Get a product by its ID.
     *
     * @param id Product UUID
     * @return ResponseEntity containing the product, or 404 if not found.
     */
    public ResponseEntity<ProductModel> serviceGetProductById(UUID id) {
    	Optional<ProductModel> product = productRepository.findById(id);
        
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
            		.header("X-Custom-Header", "Produto encontrado com sucesso!")
                    .body(product.get());
        } else {
        	throw new ResourceNotFoundException("Produto não encontrado!");
        }
    }
    
    
    /**
     * Get all products.
     *
     * @return List of all products in the system.
     
    public ResponseEntity<List<ProductModel>> serviceGetAllProducts() {
        List<ProductModel> products = productRepository.findAll();
        // Verifica se há produtos na base de dados
	    long productCount = productRepository.count();
	    if (productCount == 0) {
	        // Se não houver produtos, retorna um código 404 (Não Encontrado)
	    	throw new ResourceNotFoundException("Nenhum produto encontrado!");
	    }
        return ResponseEntity.status(HttpStatus.OK)
        		.header("X-Custom-Header", "Produtos listados com sucesso!")
                .body(products);
    }
    */
    
    
    /*
     * Get all products. -> With Pagination
     * @param Page and Size
     * @return List of parameters with pagination
     */
    public ResponseEntity<Page<ProductModel>> serviceGetAllProducts(Pageable pageable) {
        Page<ProductModel> productsPage = productRepository.findAll(pageable);

        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum produto encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .header("X-Custom-Header", "Produtos listados com sucesso!")
                .body(productsPage);
    }


    
    
    /**
     * Update an existing product.
     *
     * @param id Product UUID
     * @param productRecordDto DTO containing the updated product information
     * @return ResponseEntity containing the updated product.
     */
    public ResponseEntity<ProductModel> serviceUpdateProduct(
            @PathVariable("id") UUID id,
            @Validated @RequestBody ProductRecordDto productRecordDto) {

        // Check if the product exists
        Optional<ProductModel> existingProduct = productRepository.findById(id);

        if (existingProduct.isEmpty()) {
        	throw new ResourceNotFoundException("Produto não encontrado!");
        }

        // Update the existing product with the new data
        ProductModel productToUpdate = existingProduct.get();
        productToUpdate.setName(productRecordDto.name());
        productToUpdate.setValue(productRecordDto.value());

        // Save the updated product
        ProductModel updatedProduct = productRepository.save(productToUpdate);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
        		.header("X-Custom-Header", "Produto atualizado com sucesso!")
                .body(updatedProduct);
    }
    
    
    /**
     * Delete a product by ID.
     *
     * @param id Product UUID
     * @return ResponseEntity indicating success or failure of the delete operation.
     */
    public ResponseEntity<Void> serviceDeleteProduct(@PathVariable("id") UUID id) {
        // Check if the product exists
        Optional<ProductModel> product = productRepository.findById(id);

        if (product.isEmpty()) {
        	throw new ResourceNotFoundException("Produto não encontrado!");
        }

        // Delete the product
        productRepository.delete(product.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
        		.header("X-Custom-Header", "Produto deletado com sucesso!!")
        		.build(); // 204 Not Found
    }
    
    
    /**
     * Delete all products.
     *
     * @return ResponseEntity indicating success or failure of the delete operation.
     */
    public ResponseEntity<Void> serviceDeleteAllProducts() {
        // Verifica se há produtos na base de dados
	    long productCount = productRepository.count();
	    if (productCount == 0) {
	        // Se não houver produtos, retorna um código 404 (Não Encontrado)
	    	throw new ResourceNotFoundException("Nenhum produto encontrado!");
	    }
	    
	    // Deleta todos os produtos
	    productRepository.deleteAll();
	    return ResponseEntity.status(HttpStatus.NO_CONTENT)
        		.header("X-Custom-Header", "Produtos deletados com sucesso!!")
        		.build(); // 204 Not Found
	    

        
    }
}