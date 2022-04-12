package com.twognation.ecommerce.service;

import com.twognation.ecommerce.dto.ImageSizeDto;
import com.twognation.ecommerce.model.ImageSize;

public interface ImageSizeService {

    ImageSizeDto getImageSizeDto(Long id);
    ImageSize getImageSize(Long id);

}
