package com.twognation.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequenceGenerator")
    @SequenceGenerator(name="sequenceGenerator", allocationSize = 100)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Product name is required.")
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    List<ProductImage> productImages = new ArrayList<>();

    public Product(Long id, @NotNull(message = "Product name is required.") String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }
}
