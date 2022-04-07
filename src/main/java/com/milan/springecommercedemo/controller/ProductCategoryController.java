package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.core.convert.ConversionService;
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
    public List<ProductCategoryDto> getProductCategories() {        
        return productCategoryService.getAllProductCategories();
    }

    @GetMapping("/product-category-subcategories/{id}")
    public List<ProductCategoryDto> getProductCategorySubcategories(@PathVariable Long id) {
        return productCategoryService.getAllCategorySubcategoriesDtos(id);
    }

    @GetMapping("/product-categories/{id}")
    public ProductCategoryDto getProductCategory(@PathVariable Long id) {
        return productCategoryService.getProductCategoryDto(id);
    }

    @PostMapping("/product-categories")
    public ProductCategoryDto createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryService.saveProductCategory(productCategoryDto);
    }

    @PutMapping("/product-categories")
    public ProductCategoryDto updateProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryService.saveProductCategory(productCategoryDto);
    }

    @DeleteMapping("/product-categories")
    public void deleteProductCategory(@RequestParam Long id) {
        productCategoryService.deleteProductCategory(id);
    }
}
