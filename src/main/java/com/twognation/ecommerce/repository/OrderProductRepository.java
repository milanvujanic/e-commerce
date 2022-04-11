package com.twognation.ecommerce.repository;

import com.twognation.ecommerce.model.OrderProduct;
import com.twognation.ecommerce.model.OrderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {
    List<OrderProduct> findByOrder_Id(Long id);
}
