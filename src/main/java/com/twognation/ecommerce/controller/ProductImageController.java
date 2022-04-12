package com.twognation.ecommerce.controller;

import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.service.ProductImageService;
import liquibase.pro.packaged.R;
import org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @GetMapping("/product-images/{id}")
    public ResponseEntity<List<ProductImageDto>> getProductImagesForProduct(@PathVariable Long id) {
        List<ProductImageDto> productImageDtos = productImageService.findByProductDtosByProductId(id);
        return new ResponseEntity<>(productImageDtos, HttpStatus.OK);
    }

    @PostMapping("/product-images")
    public ResponseEntity<Void> saveProductImages(@RequestBody List<ProductImageDto> productImageDtos) {
        productImageService.saveAllDtos(productImageDtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/product-images")
    public ResponseEntity<Void> updateProductImages(@RequestBody List<ProductImageDto> productImageDtos) {
        productImageService.batchUpdateProductImages(productImageDtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product-images/{id}")
    public ResponseEntity<Void> deleteProductImage(@PathVariable Long id) {
        productImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


















