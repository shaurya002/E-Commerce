package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Controller",
        description = "Operations related to products")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @Operation(summary = "Get product by ID")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Create a new product")
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing product")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestBody Product product){

        Product existingProduct = productService.getProductById(id);

        if(existingProduct == null){
            throw new RuntimeException("Product not found");
        }

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setAvailable(product.isAvailable());
        existingProduct.setQuantity(product.getQuantity());

        return new ResponseEntity<>(productService.updateProduct(existingProduct), HttpStatus.OK);
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product existingProduct = productService.getProductById(id);

        if(existingProduct == null){
            throw new RuntimeException("Product not found");
        }

        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted", HttpStatus.OK);
    }
}
