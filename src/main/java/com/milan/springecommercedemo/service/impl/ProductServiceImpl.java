package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.exception.ResourceNotFoundException;
import com.milan.springecommercedemo.model.Product;
import com.milan.springecommercedemo.repository.ProductRepository;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByProductCategoryId(Long categoryId) {
        return productRepository.findByProductCategory_Id(categoryId);
    }
}
