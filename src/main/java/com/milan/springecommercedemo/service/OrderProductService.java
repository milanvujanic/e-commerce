package com.milan.springecommercedemo.service;

import com.milan.springecommercedemo.dto.OrderProductDto;
import com.milan.springecommercedemo.dto.ProductSlimDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.model.OrderProduct;

import java.util.List;
import java.util.Map;

public interface OrderProductService {

    OrderProduct create(OrderProduct orderProduct);
    void saveAll(Iterable<OrderProduct> orderProducts);
    List<OrderProduct> findByOrderId(Long orderId);
    List<OrderProductDto> findDtosByOrderId(Long orderId);
    List<Map<String, Number>> fetchDtosMapByOrderId(Long orderId);
    List<OrderProduct> fetchOrderProducts(Order order, List<ProductSlimDto> products);
    List<ProductSlimDto> fetchOrderProductSlimDtos(Long orderId);
}
