package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ProductCategoryDto;
import com.twognation.ecommerce.model.ProductCategory;
import com.twognation.ecommerce.service.ProductCategoryService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryDtoToProductCategory implements Converter<ProductCategoryDto, ProductCategory> {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryDtoToProductCategory(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @Override
    public ProductCategory convert(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryDto.getId());
        productCategory.setName(productCategoryDto.getName());
        productCategory.setDescription(productCategoryDto.getDescription());
        productCategory.setImageUrl(productCategoryDto.getImageUrl());
        ProductCategory productCategoryParent = new ProductCategory();
        if (productCategoryDto.getParentId() != null) {
            productCategoryParent = productCategoryService.getProductCategory(productCategoryDto.getParentId());
            productCategory.setProductCategory(productCategoryParent);
        }
        return productCategory;
    }
}
