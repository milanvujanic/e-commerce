package com.twognation.ecommerce.repository;

import com.twognation.ecommerce.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProduct_Id(Long id);
    Optional<ProductImage> findByProduct_IdAndOrdinalNumber(Long id, Integer ordinalNumber);
    void deleteByProduct_Id(Long id);
}
