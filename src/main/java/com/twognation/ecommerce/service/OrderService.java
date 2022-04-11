package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.OrderDto;
import com.twognation.ecommerce.model.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderService {

    @NotNull List<OrderDto> getAllOrders();

    OrderDto create(@NotNull(message = "The order cannot be null.") @Valid OrderDto orderDto);

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid OrderDto orderDto);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);

    OrderDto getOrderDto(Long id);

    Order getOrder(Long id);

    Order saveOrderFromOrderDto(OrderDto orderDto);

}
