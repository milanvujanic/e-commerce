package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.OrderProductDto;
import com.twognation.ecommerce.model.Order;
import com.twognation.ecommerce.model.OrderProduct;
import com.twognation.ecommerce.model.Product;
import com.twognation.ecommerce.service.OrderService;
import com.twognation.ecommerce.service.ProductService;
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
        Order order = orderService.getOrder(orderProductDto.getOrderId());
        Product product = productService.getProduct(orderProductDto.getProductId());

        OrderProduct orderProduct = new OrderProduct(order, product, orderProductDto.getQuantity());
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);

        return orderProduct;
    }
}
