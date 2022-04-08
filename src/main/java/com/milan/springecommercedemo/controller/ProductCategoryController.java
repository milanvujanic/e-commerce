package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.controller.util.HttpHeaderUtil;
import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategoryDto>> getProductCategories() {
        List<ProductCategoryDto> productCategories = productCategoryService.getAllProductCategories();
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    @GetMapping("/product-category-subcategories/{id}")
    public ResponseEntity<List<ProductCategoryDto>> getProductCategorySubcategories(@PathVariable Long id) {
        List<ProductCategoryDto> categorySubcategoriesDtos = productCategoryService.getAllCategorySubcategoriesDtos(id);
        return new ResponseEntity<>(categorySubcategoriesDtos, HttpStatus.OK);
    }

    @GetMapping("/product-categories/{id}")
    public ResponseEntity<ProductCategoryDto> getProductCategory(@PathVariable Long id) {
        ProductCategoryDto productCategoryDto = productCategoryService.getProductCategoryDto(id);
        return new ResponseEntity<>(productCategoryDto, HttpStatus.OK);
    }

    @PostMapping("/product-categories")
    public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        ProductCategoryDto productCategoryDtoTemp = productCategoryService.saveProductCategory(productCategoryDto);
        HttpHeaders headers = HttpHeaderUtil.createLocation("/product-categories/{id}", productCategoryDtoTemp.getId());
        return new ResponseEntity<>(productCategoryDtoTemp, headers, HttpStatus.CREATED);
    }

    @PutMapping("/product-categories")
    public ResponseEntity<ProductCategoryDto> updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        ProductCategoryDto productCategoryDtoTemp = productCategoryService.saveProductCategory(productCategoryDto);
        return new ResponseEntity<>(productCategoryDtoTemp, HttpStatus.OK);
    }

    @DeleteMapping("/product-categories")
    public ResponseEntity<Void> deleteProductCategory(@RequestParam Long id) {
        productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
