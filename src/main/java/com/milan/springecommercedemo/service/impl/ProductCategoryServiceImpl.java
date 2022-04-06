package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.model.ProductCategory;
import com.milan.springecommercedemo.repository.ProductCategoryRepository;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategory(Long id) {
        return productCategoryRepository.getById(id);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

}
