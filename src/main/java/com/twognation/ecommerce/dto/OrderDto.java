package com.twognation.ecommerce.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderDto {
    private Long id;
    private ZonedDateTime dateCreated;
    private String status;
    private List<ProductSlimDto> products;
    private Double totalOrderPrice;
    private int numberOfProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime created) {
        this.dateCreated = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductSlimDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSlimDto> products) {
        this.products = products;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
