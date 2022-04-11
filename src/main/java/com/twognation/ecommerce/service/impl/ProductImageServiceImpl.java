package com.twognation.ecommerce.service.impl;

import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.exception.ResourceNotFoundException;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.model.ProductImage;
import com.twognation.ecommerce.repository.ProductImageRepository;
import com.twognation.ecommerce.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

    @Override
    public List<ProductImageDto> findByProductDtosByProductId(Long id) {
        return productImageRepository.findByProduct_Id(id).stream()
                .map(productImage -> conversionService.convert(productImage, ProductImageDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDto findProductImageDtoByProductIdAndOrdinalNumber(Long id, Integer ordinalNumber) {
        return productImageRepository.findByProduct_IdAndOrdinalNumber(id, ordinalNumber)
                .map(productImage -> conversionService.convert(productImage, ProductImageDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("ProductImageNotFoune not found: productId = " + id + ", ordinalNumber = " + ordinalNumber));
    }

    @Override
    public ProductImageDto save(ProductImageDto productImageDto) {
        ProductImage convert = conversionService.convert(productImageDto, ProductImage.class);
        convert = productImageRepository.save(convert);
        productImageDto.setId(convert.getId());
        return productImageDto;
    }

    @Override
    public void delete(Long id) {
        productImageRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<ProductImage> productImages) {
        productImageRepository.saveAll(productImages);
    }

    @Override
    public void deleteByProductId(Long id) {
        productImageRepository.deleteByProduct_Id(id);
    }
}
