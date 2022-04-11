package com.twognation.ecommerce.service.impl;

import com.twognation.ecommerce.dto.OrderDto;
import com.twognation.ecommerce.exception.BadRequestException;
import com.twognation.ecommerce.model.Order;
import com.twognation.ecommerce.model.OrderProduct;
import com.twognation.ecommerce.model.OrderStatus;
import com.twognation.ecommerce.repository.OrderRepository;
import com.twognation.ecommerce.service.OrderProductService;
import com.twognation.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
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
        orderDto.setDateCreated(OffsetDateTime.now());
        Order order = orderRepository.save(conversionService.convert(orderDto, Order.class));
        orderDto.setId(order.getId());
        return orderDto;
    }

    @Override
    public Order create(Order order) {
        order.setDateCreated(OffsetDateTime.now());
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
        if (orderDto.getId() != null) {
            throw new BadRequestException("Order's id must be null but is: id = " + orderDto.getId());
        }
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = create(order);
        List<OrderProduct> orderProducts = orderProductService.fetchOrderProducts(order, orderDto.getProducts());

        order.setOrderProducts(orderProducts);
        orderProductService.saveAll(orderProducts);

        update(order);
        return order;
    }

}
