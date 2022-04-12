package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ProductDto;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.service.ProductCategoryService;
import com.twognation.ecommerce.service.ProductImageService;
import com.twognation.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoToProduct implements Converter<ProductDto, Product> {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setProductCategory(productCategoryService.getProductCategory(productDto.getProductCategoryId()));
        product.setProductImages(productImageService.fetchProductImagesList(productDto.getProductImages(), product));
        return product;
    }
}
