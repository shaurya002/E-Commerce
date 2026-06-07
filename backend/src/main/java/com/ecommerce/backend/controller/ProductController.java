package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(
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

        return productService.updateProduct(existingProduct);
    }

    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable int id){
        Product existingProduct = productService.getProductById(id);

        if(existingProduct == null){
            throw new RuntimeException("Product not found");
        }

        productService.deleteProduct(id);
        return "Product deleted";
    }






}
