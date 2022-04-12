package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.model.ProductImage;
import com.twognation.ecommerce.service.ImageSizeService;
import com.twognation.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductImageDtoToProductImage implements Converter<ProductImageDto, ProductImage> {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageSizeService imageSizeService;

    @Override
    public ProductImage convert(ProductImageDto productImageDto) {
        ProductImage productImage = new ProductImage();
        productImage.setId(productImageDto.getId());
        productImage.setImage(productImageDto.getImage());
        productImage.setOrdinalNumber(productImageDto.getOrdinalNumber());
        productImage.setImageFormat(productImageDto.getImageFormat());
        productImage.setProduct(productService.getProduct(productImageDto.getProductId()));
        productImage.setImageSize(imageSizeService.getImageSize(productImageDto.getImageSizeId()));
        return productImage;
    }
}
