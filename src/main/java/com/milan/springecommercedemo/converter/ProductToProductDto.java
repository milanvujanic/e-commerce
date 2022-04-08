package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.ProductDto;
import com.milan.springecommercedemo.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setPictureUrl(product.getPictureUrl());
        productDto.setProductCategoryId(product.getProductCategory().getId());
        return productDto;
    }
}