package com.twognation.ecommerce.service.impl;

import com.twognation.ecommerce.dto.ProductDto;
import com.twognation.ecommerce.dto.ProductImageDto;
import com.twognation.ecommerce.exception.ResourceNotFoundException;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.model.ProductImage;
import com.twognation.ecommerce.repository.ProductRepository;
import com.twognation.ecommerce.service.ProductImageService;
import com.twognation.ecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductImageService productImageService;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

    public ProductServiceImpl(ProductRepository productRepository, ProductImageService productImageService) {
        this.productRepository = productRepository;
        this.productImageService = productImageService;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> conversionService.convert(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductDto(long id) {
        return productRepository
          .findById(id)
          .map(product -> conversionService.convert(product, ProductDto.class))
          .orElseThrow(() -> new ResourceNotFoundException("Product not found: id = " + id));
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.getById(id);
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product convert = conversionService.convert(productDto, Product.class);
        convert = productRepository.save(convert);
        List<ProductImage> productImages = fetchProductImagesList(productDto.getProductImages(), convert);
        productImageService.saveAll(productImages);
        productDto.setId(convert.getId());
        return productDto;
    }

    @Override
    public List<ProductImage> fetchProductImagesList(Map<String, List<ProductImageDto>> productImages, Product product) {
        return productImages.keySet().stream()
                .flatMap(key -> productImages.get(key).stream())
                .map(productImageDto -> {
                    ProductImage productImage = conversionService.convert(productImageDto, ProductImage.class);
                    productImage.setProduct(product);
                    return productImage;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<ProductImageDto>> fetchProductImagesMap(List<ProductImage> productImages) {
        Map<String, List<ProductImageDto>> productImageDtosMap = new HashMap<>();
        for (ProductImage productImage : productImages) {
            if (productImage.getSmallImage() != null)
        }

    }

    @Override
    public ProductDto update(ProductDto productDto) {
        Product convert = conversionService.convert(productDto, Product.class);
        convert = productRepository.save(convert);
        List<ProductImage> productImages = fetchProductImagesList(productDto.getProductImages(), convert);
        productImageService.deleteByProductId(productDto.getId());
        productImageService.saveAll(productImages);
        return productDto;
    }

    @Override
    public List<ProductDto> findByProductCategoryId(Long categoryId) {
        return productRepository.findByProductCategory_Id(categoryId).stream()
                .map(product -> conversionService.convert(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteByProductCategoryIdIn(List<Long> ids) {
        productRepository.deleteByProductCategory_IdIn(ids);
    }

}
