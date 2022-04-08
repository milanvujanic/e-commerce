package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.controller.util.HttpHeaderUtil;
import com.milan.springecommercedemo.dto.ProductDto;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @NotNull ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * sadgasd;lgjas ;lkadsjg as;dgj
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto productDto = productService.getProductDto(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/products-by-category/{id}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long id) {
        List<ProductDto> productDtos = productService.findByProductCategoryId(id);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto productDtoTemp = productService.save(productDto);
        HttpHeaders headers = HttpHeaderUtil.createLocation("/product-categories/{id}", productDtoTemp.getId());
        return new ResponseEntity<>(productDtoTemp, headers, HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        ProductDto productDtoTemp = productService.update(productDto);
        return new ResponseEntity<>(productDtoTemp, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
