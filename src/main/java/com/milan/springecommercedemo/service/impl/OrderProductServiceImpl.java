package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.dto.OrderDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.model.OrderStatus;
import com.milan.springecommercedemo.model.Product;
import com.milan.springecommercedemo.repository.OrderProductRepository;
import com.milan.springecommercedemo.service.OrderProductService;
import com.milan.springecommercedemo.service.OrderService;
import com.milan.springecommercedemo.service.ProductService;
import liquibase.pro.packaged.A;
import liquibase.pro.packaged.L;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    @Lazy
    private OrderService orderService;

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

    @Override
    public List<OrderProduct> findByOrderId(Long orderId) {
        return orderProductRepository.findByOrder_Id(orderId);
    }

    @Override
    public List<OrderProduct> fetchOrderProducts(Order order, List<Long> productIds, List<Integer> pruductQuantities) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            Product product = productService.getProduct(productIds.get(i));
            OrderProduct orderProduct = new OrderProduct(order, product, pruductQuantities.get(i));
            orderProduct.setOrder(order);
            orderProduct.setProduct(product);
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }

}
