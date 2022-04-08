package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.controller.util.HttpHeaderUtil;
import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.service.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public @NotNull ResponseEntity<List<OrderDto>> list() {
        List<OrderDto> orderDtos = this.orderService.getAllOrders();
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id) {
        OrderDto orderDto = orderService.getOrderDto(id);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> create(@RequestBody OrderDto orderDto) {
        Order order = orderService.saveOrderFromOrderDto(orderDto);
        HttpHeaders headers = HttpHeaderUtil.createLocation("/orders/{id}", order.getId());
        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

}
