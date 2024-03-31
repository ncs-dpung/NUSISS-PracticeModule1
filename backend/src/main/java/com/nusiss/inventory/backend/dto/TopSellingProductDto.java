package com.nusiss.inventory.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopSellingProductDto {
  private String productName;
  private long totalQuantity;
}
