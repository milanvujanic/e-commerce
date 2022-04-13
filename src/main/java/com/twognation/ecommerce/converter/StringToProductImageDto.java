package com.twognation.ecommerce.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twognation.ecommerce.dto.ProductImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StringToProductImageDto implements Converter<String, List<ProductImageDto>> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<ProductImageDto> convert(String productImageDtoString) {
        System.out.println(productImageDtoString);
        try {
            return Arrays.asList(objectMapper.readValue(productImageDtoString, ProductImageDto[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
