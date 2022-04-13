package com.twognation.ecommerce.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductImageDto> productImages;
    private Double price;
    private Long productCategoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductImageDto> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImageDto> productImages) {
        this.productImages = productImages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
}
