package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.model.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImageDto> findByProductDtosByProductId(Long id);
    ProductImageDto findProductImageDtoByProductIdAndOrdinalNumber(Long id, Integer ordinalNumber);
    ProductImageDto save(ProductImageDto productImageDto);
    void delete(Long id);
    void saveAll(List<ProductImage> productImages);
    void deleteByProductId(Long id);
}
