package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
        // This is a placeholder for product-related endpoints
        // You can add methods here to handle CRUD operations for products

    @GetMapping("/products")
    public static List<Product> getProducts(){
        return null;
    }

    @GetMapping("products/{id}")
    public static Product getProduct(int id){
        return null;
    }

    @PostMapping("/products")
    public static Product createProduct(Product product){
        return null;
    }

    @PatchMapping("products/{id}")
    public static Product updateProduct(int id){
        return null;
    }

    @DeleteMapping("products/{id}")
    public static Product deleteProduct(int id){
        return null;
    }






}
