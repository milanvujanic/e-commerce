package com.milan.springecommercedemo.service;

import com.milan.springecommercedemo.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getAllProductCategories();
    ProductCategory getProductCategory(Long id);
    ProductCategory saveProductCategory(ProductCategory productCategory);
}
