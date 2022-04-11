package com.twognation.ecommerce.dto;

import java.util.List;

public class ProductCategoryDto {
    private Long id;
    private Long parentId;
    private List<ProductCategorySlimDto> subcategories;
    private String name;
    private String description;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ProductCategorySlimDto> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<ProductCategorySlimDto> subcategories) {
        this.subcategories = subcategories;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
