package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ProductDto;
import com.twognation.ecommerce.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setProductImages(null);
        productDto.setPrice(product.getPrice());
        productDto.setProductCategoryId(product.getProductCategory().getId());
        return productDto;
    }
}
