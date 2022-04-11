package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.model.ProductImage;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductImageToProductImageDto implements Converter<ProductImage, ProductImageDto> {

    @Override
    public ProductImageDto convert(ProductImage productImage) {
        ProductImageDto productImageDto = new ProductImageDto();
        productImageDto.setId(productImage.getId());
        productImageDto.setProductId(productImage.getId());
        productImageDto.setSmallImage(productImage.getSmallImage());
        productImageDto.setLargeImage(productImage.getLargeImage());
        productImageDto.setOrdinalNumber(productImage.getOrdinalNumber());
        productImageDto.setImageFormat(productImage.getImageFormat());
        return productImageDto;
    }
}
