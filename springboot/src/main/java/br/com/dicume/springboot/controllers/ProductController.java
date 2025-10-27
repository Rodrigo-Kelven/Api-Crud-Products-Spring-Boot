package br.com.dicume.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import br.com.dicume.springboot.dtos.ProductRecordDto;
import br.com.dicume.springboot.models.ProductModel;
import br.com.dicume.springboot.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "API Products", description = "Operations relational of Products API")
public class ProductController {


    
    @Autowired
    private ProductService productService;

    
    @PostMapping("/create")
    @Operation(summary = "Create a new product", description = "Creates a new product in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad Request, invalid input data")
    })
    public ResponseEntity<ProductModel> saveProduct(@Validated @RequestBody ProductRecordDto productRecordDto) {        
        return productService.serviceSaveProduct(productRecordDto);
    }

    
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID", description = "Fetches a product based on its UUID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product found"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") UUID id) {
    	return productService.serviceGetProductById(id);
    }


    
    @GetMapping("/")
    @Operation(summary = "Get all products", description = "Fetches a list of all products in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of products returned"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProductModel>> getAllProducts() {
    	return productService.serviceGetAllProducts();
    }

    
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a product", description = "Updates an existing product based on its UUID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "400", description = "Bad Request, invalid input data"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductModel> updateProduct(
            @PathVariable("id") UUID id,
            @Validated @RequestBody ProductRecordDto productRecordDto) {

    	return productService.serviceUpdateProduct(id, productRecordDto);
    }

    
    
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product based on its UUID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") UUID id) {
    	return productService.serviceDeleteProduct(id);
    }
    
    
    
    @DeleteMapping("/delete-all")
    @Operation(summary = "Delete all products", description = "Deletes all products from the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "All products deleted successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteAllProducts() {
    	return productService.serviceDeleteAllProducts();

        
    }
}
