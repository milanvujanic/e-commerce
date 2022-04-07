package com.milan.springecommercedemo.service;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryDto> getAllProductCategories();
    ProductCategoryDto getProductCategoryDto(Long id);

    ProductCategory getProductCategory(Long id);
    ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto);

    ProductCategory saveProductCategory(ProductCategory productCategory);

    void deleteProductCategory(Long id);

    List<ProductCategoryDto> getAllCategorySubcategoriesDtos(Long id);

    List<ProductCategory> getAllCategorySubcategories(Long id);
}
