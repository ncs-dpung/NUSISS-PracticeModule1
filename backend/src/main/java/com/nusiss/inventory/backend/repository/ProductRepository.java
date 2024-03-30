package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

//    @Query("SELECT new com.nusiss.inventory.backend.dto.TopSellingProductDto(p.name, SUM(oi.quantity) as totalQuantity) " +
//            "FROM OrderItem oi JOIN oi.product p " +
//            "GROUP BY p.id " +
//            "ORDER BY totalQuantity DESC " +
//            "LIMIT 6")
//    List<TopSellingProductDto> findTopSellingProducts();

    @Query(value = "SELECT p.name, SUM(oi.quantity) AS totalQuantity " +
            "FROM tbl_order_items oi JOIN tbl_product p ON oi.product_id = p.product_id JOIN tbl_order o ON oi.order_id = o.order_id " +
            "WHERE YEAR(o.date_placed) = :year AND MONTH(o.date_placed) = :month " +
            "GROUP BY p.product_id " +
            "ORDER BY totalQuantity DESC LIMIT 6", nativeQuery = true)
    List<Object[]> findTopSellingProducts(@Param("year") int year, @Param("month") int month);

}

