package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.category JOIN FETCH p.supplier")
    List<Product> findAllWithDetails();

    @Query("SELECT p FROM Product p WHERE p.quantityAvailable <= p.reorderLevel")
    List<Product> findProductsNeedingReorder();

    long countBySupplierId(Long supplierId);

    List<Product> findByIdIn(Set<Long> ids);
}

