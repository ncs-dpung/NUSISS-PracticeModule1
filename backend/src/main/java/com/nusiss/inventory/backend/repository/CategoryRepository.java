package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new com.nusiss.inventory.backend.dto.CategoryRevenueDto(c.categoryName, SUM(oi.price) as revenue) " +
            "FROM OrderItem oi JOIN oi.product p JOIN p.category c " +
            "GROUP BY c.categoryId " +
            "ORDER BY revenue DESC")
    List<CategoryRevenueDto> findRevenueByCategory();
}
