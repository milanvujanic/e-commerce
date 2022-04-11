package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.OrderDto;
import com.twognation.ecommerce.model.Order;
import com.twognation.ecommerce.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDto implements Converter<Order, OrderDto> {

    @Autowired
    private OrderProductService orderProductService;

    @Override
    public OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDateCreated(order.getDateCreated());
        orderDto.setProducts(orderProductService.fetchOrderProductSlimDtos(order.getId()));
        orderDto.setTotalOrderPrice(getTotalOrderPrice(order));
        orderDto.setNumberOfProducts(order.getOrderProducts().size());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }

    private Double getTotalOrderPrice(Order order) {
        return order.getOrderProducts().stream()
                .mapToDouble(orderProduct -> orderProduct.getTotalPrice())
                .sum();
    }
}
