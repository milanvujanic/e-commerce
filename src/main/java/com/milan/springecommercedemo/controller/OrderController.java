package com.milan.springecommercedemo.controller;

import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.service.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public @NotNull List<OrderDto> list() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getOrderDto(id);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> create(@RequestBody OrderDto orderDto) {
        Order order = orderService.saveOrderFromOrderDto(orderDto);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/orders/{id}")
          .buildAndExpand(order.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

}
