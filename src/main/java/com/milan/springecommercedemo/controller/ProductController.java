package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.dto.ProductDto;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public @NotNull List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        return productService.getProductDto(id);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/products")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.update(productDto);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
