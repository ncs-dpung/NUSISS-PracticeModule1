package com.nusiss.inventory.backend.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRevenueDto {
  private String categoryName;
  private BigDecimal revenue;
}
