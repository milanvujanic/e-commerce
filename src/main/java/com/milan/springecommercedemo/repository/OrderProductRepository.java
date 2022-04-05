package com.milan.springecommercedemo.repository;

import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
