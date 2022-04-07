package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.ProductDto;
import com.milan.springecommercedemo.model.Product;
import com.milan.springecommercedemo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
