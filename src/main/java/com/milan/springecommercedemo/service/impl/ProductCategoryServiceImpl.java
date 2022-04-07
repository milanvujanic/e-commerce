package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.model.ProductCategory;
import com.milan.springecommercedemo.repository.ProductCategoryRepository;
import com.milan.springecommercedemo.service.ProductCategoryService;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

    @Autowired
    @Lazy
    private ProductService productService;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategories() {
        return productCategoryRepository.findAll().stream()
                .map(productCategory -> conversionService.convert(productCategory, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDto getProductCategoryDto(Long id) {
        return productCategoryRepository.findById(id)
                .map(productCategory -> conversionService.convert(productCategory, ProductCategoryDto.class))
                .get();
    }

    @Override
    public ProductCategory getProductCategory(Long id) {
        return productCategoryRepository.getById(id);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.getById(id);
        List<ProductCategory> productCategories = getAllCategorySubcategories(id);
        productCategories.add(0, productCategory);

        productService.deleteByProductCategoryIdIn(productCategories.stream()
                .map(pc -> pc.getId())
                .collect(Collectors.toList()));
        productCategoryRepository.deleteAll(productCategories);
    }

    @Override
    public List<ProductCategoryDto> getAllCategorySubcategoriesDtos(Long id) {
        return productCategoryRepository.findByProductCategory_Id(id).stream()
                .map(productCategory -> conversionService.convert(productCategory, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductCategory> getAllCategorySubcategories(Long id) {
        return productCategoryRepository.findByProductCategory_Id(id);
    }

    @Override
    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategory convert = conversionService.convert(productCategoryDto, ProductCategory.class);
        ProductCategory productCategory = productCategoryRepository.save(convert);
        productCategoryDto.setId(productCategory.getId());
        return productCategoryDto;
    }

}
