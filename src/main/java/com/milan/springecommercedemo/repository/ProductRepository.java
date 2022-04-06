package com.milan.springecommercedemo.repository;

import com.milan.springecommercedemo.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByProductCategory_Id(Long id);
}
