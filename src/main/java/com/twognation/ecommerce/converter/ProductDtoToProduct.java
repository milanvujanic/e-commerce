package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ProductDto;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProduct implements Converter<ProductDto, Product> {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setPictureUrl(productDto.getPictureUrl());
        product.setProductCategory(productCategoryService.getProductCategory(productDto.getProductCategoryId()));
        return product;
    }
}
