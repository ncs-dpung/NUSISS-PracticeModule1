package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.entity.Product;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p JOIN FETCH p.category JOIN FETCH p.supplier")
  List<Product> findAllWithDetails();

  @Query("SELECT p FROM Product p WHERE p.quantityAvailable <= p.reorderLevel")
  List<Product> findProductsNeedingReorder();

  long countBySupplierId(Long supplierId);

  List<Product> findByIdIn(Set<Long> ids);

  @Query(
      "SELECT new com.nusiss.inventory.backend.dto.TopSellingProductDto(p.name, SUM(oi.quantity) as"
          + " totalQuantity) FROM OrderItem oi JOIN oi.product p GROUP BY p.id ORDER BY"
          + " totalQuantity DESC LIMIT 6")
  List<TopSellingProductDto> findTopSellingProducts();
}
