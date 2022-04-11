package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.OrderDto;
import com.twognation.ecommerce.model.Order;
import com.twognation.ecommerce.service.OrderProductService;
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
