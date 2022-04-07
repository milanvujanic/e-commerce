package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.model.OrderStatus;
import com.milan.springecommercedemo.repository.OrderRepository;
import com.milan.springecommercedemo.service.OrderProductService;
import com.milan.springecommercedemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

    @Autowired
    @Lazy
    private OrderProductService orderProductService;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return this.orderRepository.findAll().stream()
                .map(order -> conversionService.convert(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        orderDto.setDateCreated(LocalDate.now());
        Order order = orderRepository.save(conversionService.convert(orderDto, Order.class));
        orderDto.setId(order.getId());
        return orderDto;
    }

    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return orderRepository.save(order);
    }

    @Override
    public void update(OrderDto orderDto) {
        this.orderRepository.save(conversionService.convert(orderDto, Order.class));
    }

    @Override
    public void update(Order order) {
        orderRepository.save(order);
    }

    @Override
    public OrderDto getOrderDto(Long id) {
        return orderRepository.findById(id).map(order -> conversionService.convert(order, OrderDto.class)).get();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order saveOrderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = create(order);
        List<OrderProduct> orderProducts = orderProductService.fetchOrderProducts(order, orderDto.getProductIds(), orderDto.getProductQuantities());

        order.setOrderProducts(orderProducts);
        orderProductService.saveAll(orderProducts);

        update(order);
        return order;
    }
}
