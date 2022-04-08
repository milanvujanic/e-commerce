package com.milan.springecommercedemo.service.impl;

import com.milan.springecommercedemo.dto.OrderProductDto;
import com.milan.springecommercedemo.dto.ProductSlimDto;
import com.milan.springecommercedemo.model.Order;
import com.milan.springecommercedemo.model.OrderProduct;
import com.milan.springecommercedemo.model.Product;
import com.milan.springecommercedemo.repository.OrderProductRepository;
import com.milan.springecommercedemo.service.OrderProductService;
import com.milan.springecommercedemo.service.OrderService;
import com.milan.springecommercedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    @Lazy
    private OrderService orderService;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

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
    public List<OrderProductDto> findDtosByOrderId(Long orderId) {
        return findByOrderId(orderId).stream()
                .map(orderProduct -> conversionService.convert(orderProduct, OrderProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Number>> fetchDtosMapByOrderId(Long orderId) {
        List<OrderProductDto> orderProductDtos = findDtosByOrderId(orderId);
        List<Map<String, Number>> orderProductDtoList = new ArrayList<>();
        for (OrderProductDto orderProductDto : orderProductDtos) {
            Map<String, Number> orderProductDtoMap = new HashMap<>();
            orderProductDtoMap.put("productId", orderProductDto.getProductId());
            orderProductDtoMap.put("quantity", orderProductDto.getQuantity());
            orderProductDtoList.add(orderProductDtoMap);
        }
        return orderProductDtoList;
    }

    @Override
    public List<OrderProduct> fetchOrderProducts(Order order, List<ProductSlimDto> productSlimDtos) {
        return productSlimDtos.stream()
                .map(productSlimDto -> {
                        Product product = productService.getProduct(productSlimDto.getId());
                        OrderProduct orderProduct = new OrderProduct(order, product, productSlimDto.getQuantity());
                        orderProduct.setOrder(order);
                        orderProduct.setProduct(product);
                        return orderProduct;
                    }).collect(Collectors.toList());
    }

    @Override
    public List<ProductSlimDto> fetchOrderProductSlimDtos(Long orderId) {
        return findDtosByOrderId(orderId).stream()
                .map(orderProductDto -> {
                    ProductSlimDto productSlimDto = new ProductSlimDto();
                    productSlimDto.setId(orderProductDto.getProductId());
                    productSlimDto.setQuantity(orderProductDto.getQuantity());
                    return productSlimDto;
                }).collect(Collectors.toList());
    }

}
