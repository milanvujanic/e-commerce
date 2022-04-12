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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final Environment environment;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

    public ProductImageServiceImpl(Environment environment) {
        this.environment = environment;
    }

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
    public void saveAllDtos(List<ProductImageDto> productImageDtos) {
        List<ProductImage> productImages = productImageDtos.stream()
                .map(productImageDto -> conversionService.convert(productImageDto, ProductImage.class))
                .collect(Collectors.toList());
        productImageRepository.saveAll(productImages);
    }

    @Override
    public void deleteByProductId(Long id) {
        productImageRepository.deleteByProduct_Id(id);
    }

    @Override
    public void batchUpdateProductImages(List<ProductImageDto> productImageDtos) {
        List<Long> productImageIds = new ArrayList<>();
        List<ProductImage> productImages = productImageDtos.stream()
                .map(productImageDto -> {
                    ProductImage productImage = conversionService.convert(productImageDto, ProductImage.class);
                    productImageIds.add(productImage.getId());
                    return productImage;
                })
                .collect(Collectors.toList());
        TypedQuery<ProductImage> productImageTypedQuery =
                entityManager.createQuery("SELECT productImage from ProductImage productImage where productImage.id in (?1) order by productImage.id", ProductImage.class);
        List<ProductImage> productImagesToUpdate = productImageTypedQuery.setParameter(1, productImageIds).getResultList();
        int batchSize = Integer.valueOf(environment.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
        for (int i = 0; i < productImagesToUpdate.size(); i++) {
            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            productImagesToUpdate.set(i, productImagesToUpdate.get(i));
        }
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
}
