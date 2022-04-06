package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.repository.OrderProductRepository;
import com.milan.springecommercedemo.service.OrderProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }

    @Override
    public void saveAll(Iterable<OrderProduct> orderProducts) {
        this.orderProductRepository.saveAll(orderProducts);
    }
}
