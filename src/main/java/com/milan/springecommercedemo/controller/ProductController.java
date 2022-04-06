package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.model.Product;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}
