package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.ProductDto;
import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.model.ProductImage;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Validated
public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductDto(long id);

    Product getProduct(long id);

    ProductDto save(ProductDto productDto);

    List<ProductImage> fetchProductImagesList(Map<String, List<ProductImageDto>> productImages, Product product);

    Map<String, List<ProductImageDto>> fetchProductImagesMap(List<ProductImage> productImages);

    ProductDto update(ProductDto productDto);

    List<ProductDto> findByProductCategoryId(Long id);

    void delete(Long id);

    void deleteByProductCategoryIdIn(List<Long> ids);

}
