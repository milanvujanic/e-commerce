package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToOrder implements Converter<OrderDto, Order> {

    @Autowired
    private OrderProductService orderProductService;

    @Override
    public Order convert(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDateCreated(orderDto.getDateCreated());
        order.setStatus(orderDto.getStatus());
        order.setOrderProducts(orderProductService.findByOrderId(orderDto.getId()));
        return order;
    }

}
