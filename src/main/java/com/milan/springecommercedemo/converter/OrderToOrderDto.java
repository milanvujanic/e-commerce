package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.service.OrderService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderDto implements Converter<Order, OrderDto> {

    @Override
    public OrderDto convert(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDateCreated(order.getDateCreated());
        orderDto.setTotalOrderPrice(order.getTotalOrderPrice());
        orderDto.setNumberOfProducts(order.getNumberOfProducts());
        orderDto.setStatus(order.getStatus());
        return orderDto;
    }
}
