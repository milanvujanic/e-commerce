package com.twognation.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductImageDto {
    private Long id;
    private Long productId;
    private String smallImage;

    private MultipartFile smallImageFile;
    private String largeImage;

    private MultipartFile largeImageFile;
    private String title;
    private Integer ordinalNumber;
    private String imageFormat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public MultipartFile getSmallImageFile() {
        return smallImageFile;
    }

    public void setSmallImageFile(MultipartFile smallImageFile) {
        this.smallImageFile = smallImageFile;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public MultipartFile getLargeImageFile() {
        return largeImageFile;
    }

    public void setLargeImageFile(MultipartFile largeImageFile) {
        this.largeImageFile = largeImageFile;
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
}
