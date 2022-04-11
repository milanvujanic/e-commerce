package com.twognation.ecommerce.repository;

import com.twognation.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductCategory_Id(Long id);

    void deleteByProductCategory_IdIn(List<Long> ids);
}
