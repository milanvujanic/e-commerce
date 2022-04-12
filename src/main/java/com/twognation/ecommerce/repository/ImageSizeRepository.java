package com.twognation.ecommerce.repository;

import com.twognation.ecommerce.model.ImageSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageSizeRepository extends JpaRepository<ImageSize, Long> {
}
