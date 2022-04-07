package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.model.ProductCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryToProductCategoryDto implements Converter<ProductCategory, ProductCategoryDto> {

    @Override
    public ProductCategoryDto convert(ProductCategory productCategory) {
        ProductCategoryDto productCategoryDto = new ProductCategoryDto();
        productCategoryDto.setId(productCategory.getId());
        productCategoryDto.setName(productCategory.getName());
        productCategoryDto.setDescription(productCategory.getDescription());
        productCategoryDto.setImageUrl(productCategory.getImageUrl());
        productCategoryDto.setParentId(productCategory.getProductCategory().getId());
        return productCategoryDto;
    }
}
