package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.model.ProductCategory;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryToProductCategoryDto implements Converter<ProductCategory, ProductCategoryDto> {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public ProductCategoryDto convert(ProductCategory productCategory) {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setId(productCategory.getId());
        productCategoryDto.setParentId(productCategory.getProductCategory() == null ? null : productCategory.getProductCategory().getId());
        productCategoryDto.setSubcategories(productCategoryService.getProductSlimDtos(productCategory.getProductCategories()));
        productCategoryDto.setName(productCategory.getName());
        productCategoryDto.setDescription(productCategory.getDescription());
        productCategoryDto.setImageUrl(productCategory.getImageUrl());
        return productCategoryDto;
    }
}
