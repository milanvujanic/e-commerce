package com.milan.springecommercedemo.repository;

import com.milan.springecommercedemo.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
