package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.model.ProductCategory;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryDtoToProductCategory implements Converter<ProductCategoryDto, ProductCategory> {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public ProductCategory convert(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(productCategoryDto.getName());
        productCategory.setDescription(productCategoryDto.getDescription());
        productCategory.setImageUrl(productCategoryDto.getImageUrl());
        productCategory.setProductCategory(
                productCategoryDto.getParentId() == null
                ? null
                : productCategoryService.getProductCategory(productCategoryDto.getParentId()));
        return productCategory;
    }
}
