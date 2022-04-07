package com.milan.springecommercedemo.repository;

import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.model.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {
    List<OrderProduct> findByOrder_Id(Long id);
}
