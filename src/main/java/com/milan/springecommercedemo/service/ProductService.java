package com.milan.springecommercedemo.service;

import com.milan.springecommercedemo.dto.ProductDto;
import com.milan.springecommercedemo.model.Product;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductDto(long id);

    Product getProduct(long id);

    ProductDto save(ProductDto productDto);

    ProductDto update(ProductDto productDto);

    List<ProductDto> findByProductCategoryId(Long id);

    void delete(Long id);

    void deleteByProductCategoryIdIn(List<Long> ids);

}
