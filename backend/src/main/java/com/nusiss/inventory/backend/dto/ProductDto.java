package com.nusiss.inventory.backend.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
  private Long id;
  private String name;
  private Long categoryId;
  private String categoryName;
  private BigDecimal price;
  private String batchNo;
  private Long supplierId;
  private String supplierName;
  private int quantityAvailable;
  private int reorderLevel;
  private String stockLevel;
}
