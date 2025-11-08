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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
        @ApiResponse(responseCode = "400", description = "Bad Request, invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")

    })
    public ResponseEntity<ProductModel> saveProduct(@Validated @RequestBody ProductRecordDto productRecordDto) {        
        return productService.serviceSaveProduct(productRecordDto);
    }

    
    
    @GetMapping("/search-by-id/{id_product}")
    @Operation(summary = "Get a product by ID", description = "Fetches a product based on its UUID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product found"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")

    })
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id_product") UUID id) {
    	return productService.serviceGetProductById(id);
    }


    
    @GetMapping("/get-all")
    @Operation(summary = "Get all products", description = "Fetches a paginated list of all products in the system.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of products returned"),
        @ApiResponse(responseCode = "404", description = "No products found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Page<ProductModel>> getAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return productService.serviceGetAllProducts(PageRequest.of(page, size));
    }
    
    
    @GetMapping("/search-by-price")
    @Operation(summary = "Get products by price range", description = "Fetches products within a given price range.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of products returned"),
        @ApiResponse(responseCode = "404", description = "No products found within the given price range"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProductModel>> getProductsByPriceRange(
            @RequestParam(value = "minPrice") float minPrice, 
            @RequestParam(value = "maxPrice") float maxPrice) {
        // Convertendo os parâmetros Double para BigDecimal
        BigDecimal min = new BigDecimal(minPrice);
        BigDecimal max = new BigDecimal(maxPrice);
        
        return productService.serviceGetProductsByPriceRange(min, max);
    }
    
    
    @GetMapping("/search-by-name")
    @Operation(summary = "Get products by name", description = "Fetches products within a given name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of products returned"),
        @ApiResponse(responseCode = "404", description = "No products found within the given name"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProductModel>> getProductsByName(
    		@RequestParam(value = "Name Product") String nameProduct) {
    		
    	return productService.serviceGetProductsByName(nameProduct);
    		
    }
    
    @GetMapping("/search-by-supplier")
    @Operation(summary = "Get products by supplier", description = "Fetches products within a given supplier.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of products returned"),
        @ApiResponse(responseCode = "404", description = "No products found within the given supplier"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProductModel>> getProductsBySupplier(
    		@RequestParam(value = "Supplier Product") String nameSupplierProduct) {
    		
    	return productService.serviceGetProductsBySupplier(nameSupplierProduct);
    		
    }
    
    
    @GetMapping("/search-by-category")
    @Operation(summary = "Get products by category", description = "Fetches products within a given category.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of products returned"),
        @ApiResponse(responseCode = "404", description = "No products found within the given category"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ProductModel>> getProductsByCategory(
    		@RequestParam(value = "Category Product") String nameCategoryProduct) {
    		
    	return productService.serviceGetProductsByCategory(nameCategoryProduct);
    		
    }


    
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a product", description = "Updates an existing product based on its UUID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "400", description = "Bad Request, invalid input data"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")

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
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")

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