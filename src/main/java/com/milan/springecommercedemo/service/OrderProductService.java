package com.milan.springecommercedemo.service;

import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.model.OrderProduct;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Validated
public interface OrderProductService {

    OrderProduct create(OrderProduct orderProduct);
    void saveAll(Iterable<OrderProduct> orderProducts);
    List<OrderProduct> findByOrderId(Long orderId);
    List<OrderProduct> fetchOrderProducts(Order order, List<Long> productIds, List<Integer> pruductQuantitiesp);
}
