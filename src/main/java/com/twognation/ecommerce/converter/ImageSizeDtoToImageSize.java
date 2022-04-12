package com.twognation.ecommerce.converter;

import com.twognation.ecommerce.dto.ImageSizeDto;
import com.twognation.ecommerce.model.ImageSize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ImageSizeDtoToImageSize implements Converter<ImageSizeDto, ImageSize> {

    @Override
    public ImageSize convert(ImageSizeDto imageSizeDto) {
        ImageSize imageSize = new ImageSize();
        imageSize.setId(imageSizeDto.getId());
        imageSize.setName(imageSizeDto.getName());
        imageSize.setCode(imageSizeDto.getCode());
        return imageSize;
    }
}
