package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "tbl_category")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    public CategoryDto toDto() {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(categoryId);
        dto.setCategoryName(categoryName);
        return dto;
    }
}
