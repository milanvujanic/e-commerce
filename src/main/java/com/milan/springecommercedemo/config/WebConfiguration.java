package com.milan.springecommercedemo.config;

import com.milan.springecommercedemo.converter.OrderProductDtoToOrderProduct;
import com.milan.springecommercedemo.converter.ProductCategoryDtoToProductCategory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OrderProductDtoToOrderProduct());
        registry.addConverter(new ProductCategoryDtoToProductCategory());
    }
}
