package com.milan.springecommercedemo.repository;

import com.milan.springecommercedemo.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
