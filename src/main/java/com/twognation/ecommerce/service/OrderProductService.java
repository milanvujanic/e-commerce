package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.OrderProductDto;
import com.twognation.ecommerce.dto.ProductSlimDto;
import com.twognation.ecommerce.model.Order;
import com.twognation.ecommerce.model.OrderProduct;

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
