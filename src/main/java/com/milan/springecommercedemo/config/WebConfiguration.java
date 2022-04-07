package com.milan.springecommercedemo.config;

import com.milan.springecommercedemo.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new OrderProductDtoToOrderProduct());
//        registry.addConverter(new ProductCategoryDtoToProductCategory());
//        registry.addConverter(new ProductDtoToProduct());
//        registry.addConverter(new ProductToProductDto());
//        registry.addConverter(new OrderToOrderDto());
//        registry.addConverter(new OrderDtoToOrder());
//        registry.addConverter(new OrderProductToOrderProductDto());
//        registry.addConverter(new ProductCategoryToProductCategoryDto());
//    }

    @Bean("webConversionService")
    @SuppressWarnings("rawtypes")
    public ConversionService getConversionService(Set<Converter> converters) {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();

        return conversionServiceFactoryBean.getObject();
    }
}
