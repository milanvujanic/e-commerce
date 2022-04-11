package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.ProductCategoryDto;
import com.twognation.ecommerce.dto.ProductCategorySlimDto;
import com.twognation.ecommerce.model.ProductCategory;

import java.util.List;
import java.util.Set;

public interface ProductCategoryService {

    List<ProductCategoryDto> getAllProductCategories();
    ProductCategoryDto getProductCategoryDto(Long id);

    ProductCategory getProductCategory(Long id);
    ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto);

    ProductCategory saveProductCategory(ProductCategory productCategory);

    void deleteProductCategory(Long id);

    List<ProductCategoryDto> getAllCategorySubcategoriesDtos(Long id);

    List<ProductCategory> getAllCategorySubcategories(Long id);

    List<ProductCategorySlimDto> getProductSlimDtos(Set<ProductCategory> productCategories);
}
