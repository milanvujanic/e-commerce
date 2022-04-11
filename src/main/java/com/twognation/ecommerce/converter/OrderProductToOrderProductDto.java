package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.OrderProductDto;
import com.twognation.ecommerce.model.OrderProduct;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderProductToOrderProductDto implements Converter<OrderProduct, OrderProductDto> {

    @Override
    public OrderProductDto convert(OrderProduct orderProduct) {
        OrderProductDto orderProductDto = new OrderProductDto();
        orderProductDto.setOrderId(orderProduct.getOrder().getId());
        orderProductDto.setProductId(orderProduct.getProduct().getId());
        orderProductDto.setQuantity(orderProduct.getQuantity());
        return orderProductDto;
    }
}
