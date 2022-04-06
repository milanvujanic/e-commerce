package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.model.ProductCategory;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final ConversionService conversionService;

    public ProductCategoryController(ProductCategoryService productCategoryService, ConversionService conversionService) {
        this.productCategoryService = productCategoryService;
        this.conversionService = conversionService;
    }

    @GetMapping("/product-categories")
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getAllProductCategories();
    }

    @PostMapping("/product-categories")
    public ProductCategory createProductCategory(@RequestBody ProductCategoryForm productCategoryForm) {
        ProductCategoryDto productCategoryDto = productCategoryForm.getProductCategoryDto();
        ProductCategory productCategory = conversionService.convert(productCategoryDto, ProductCategory.class);
        return productCategoryService.saveProductCategory(productCategory);
    }

    public static class ProductCategoryForm {
        private String name;
        private String description;
        private String imageUrl;
        private Long parentId;

        public ProductCategoryDto getProductCategoryDto() {
            ProductCategoryDto productCategoryDto = new ProductCategoryDto();
            productCategoryDto.setName(name);
            productCategoryDto.setDescription(description);
            productCategoryDto.setImageUrl(imageUrl);
            productCategoryDto.setParentId(parentId);
            return productCategoryDto;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }
    }
}
