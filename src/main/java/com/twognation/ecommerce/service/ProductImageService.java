package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.model.ProductImage;

import java.util.List;
import java.util.Map;

public interface ProductImageService {
    List<ProductImageDto> findByProductDtosByProductId(Long id);
    ProductImageDto findProductImageDtoByProductIdAndOrdinalNumber(Long id, Integer ordinalNumber);
    ProductImageDto save(ProductImageDto productImageDto);
    void delete(Long id);
    void saveAll(List<ProductImage> productImages);
    void saveAllDtos(List<ProductImageDto> productImageDtos);
    void deleteByProductId(Long id);
    void batchUpdateProductImages(List<ProductImageDto> productImageDtos);
    List<ProductImage> fetchProductImagesList(Map<String, List<ProductImageDto>> productImages, Product product);
}
