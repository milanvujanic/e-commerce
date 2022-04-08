package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.dto.ProductCategoryDto;
import com.milan.springecommercedemo.dto.ProductCategorySlimDto;
import com.milan.springecommercedemo.exception.BadRequestException;
import com.milan.springecommercedemo.exception.ResourceNotFoundException;
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
import java.util.Set;
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
                .filter(productCategory -> productCategory.getProductCategory() == null)
                .map(productCategory -> conversionService.convert(productCategory, ProductCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDto getProductCategoryDto(Long id) {
        return productCategoryRepository.findById(id)
                .map(productCategory -> conversionService.convert(productCategory, ProductCategoryDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Product category not found: id = " + id));
    }

    @Override
    public ProductCategory getProductCategory(Long id) {
        return productCategoryRepository.getById(id);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        if (productCategory.getId() != null) {
            throw new BadRequestException("Product category's id must be null but is: id = " + productCategory.getId());
        }
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.getById(id);
        if (productCategory == null) {
            throw new BadRequestException("Product category doesn't exist: id = " + id);
        }
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
    public List<ProductCategorySlimDto> getProductSlimDtos(Set<ProductCategory> productCategories) {
        return productCategories.stream()
                .map(productCategory -> {
                    ProductCategorySlimDto productCategorySlimDto = new ProductCategorySlimDto();
                    productCategorySlimDto.setId(productCategory.getId());
                    productCategorySlimDto.setName(productCategory.getName());
                    return productCategorySlimDto;
                }).collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDto saveProductCategory(ProductCategoryDto productCategoryDto) {
        if (productCategoryDto.getId() != null) {
            throw new BadRequestException("Product category's id must be null but is: id = " + productCategoryDto.getId());
        }
        ProductCategory convert = conversionService.convert(productCategoryDto, ProductCategory.class);
        ProductCategory productCategory = productCategoryRepository.save(convert);
        productCategoryDto.setId(productCategory.getId());
        return productCategoryDto;
    }

}
