package com.twognation.ecommerce.service.impl;

import com.twognation.ecommerce.dto.ImageSizeDto;
import com.twognation.ecommerce.dto.ProductDto;
import com.twognation.ecommerce.exception.ResourceNotFoundException;
import com.twognation.ecommerce.model.ImageSize;
import com.twognation.ecommerce.repository.ImageSizeRepository;
import com.twognation.ecommerce.service.ImageSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class ImageSizeServiceImpl implements ImageSizeService {

    private final ImageSizeRepository imageSizeRepository;

    @Autowired
    @Lazy
    @Qualifier("webConversionService")
    private ConversionService conversionService;

    public ImageSizeServiceImpl(ImageSizeRepository imageSizeRepository) {
        this.imageSizeRepository = imageSizeRepository;
    }

    @Override
    public ImageSizeDto getImageSizeDto(Long id) {
        return imageSizeRepository
                .findById(id)
                .map(imageSize -> conversionService.convert(imageSize, ImageSizeDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("ImageSize not found: id = " + id));
    }

    @Override
    public ImageSize getImageSize(Long id) {
        return imageSizeRepository.getById(id);
    }
}
