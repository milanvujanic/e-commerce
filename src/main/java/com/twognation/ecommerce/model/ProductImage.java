package com.twognation.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="productImageSequence")
    @SequenceGenerator(name="productImageSequence", sequenceName = "product_image_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "small_image")
    private String smallImage;

    @Column(name = "large_image")
    private String largeImage;

    @Column(name = "title")
    private String title;

    @Column(name = "ordinal_number")
    private Integer ordinalNumber;

    @Column(name = "image_format")
    private String imageFormat;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(Integer ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
