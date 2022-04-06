package com.milan.springecommercedemo.converter;

import com.milan.springecommercedemo.dto.OrderProductDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.model.Product;
import com.milan.springecommercedemo.service.OrderService;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderProductDtoToOrderProduct implements Converter<OrderProductDto, OrderProduct> {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Override
    public OrderProduct convert(OrderProductDto orderProductDto) {
        Order order = orderService.findById(orderProductDto.getOrderId());
        Product product = productService.getProduct(orderProductDto.getProductId());

        OrderProduct orderProduct = new OrderProduct(
                orderService.findById(orderProductDto.getOrderId()),
                productService.getProduct(orderProductDto.getProductId()),
                orderProductDto.getQuantity());
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);

        return orderProduct;
    }
}
