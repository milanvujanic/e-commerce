package com.twognation.ecommerce.dto;

public class ProductImageDto {
    private Long id;
    private Long productId;
    private Long imageSizeId;
    private String image;
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

    public Long getImageSizeId() {
        return imageSizeId;
    }

    public void setImageSizeId(Long imageSizeId) {
        this.imageSizeId = imageSizeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
