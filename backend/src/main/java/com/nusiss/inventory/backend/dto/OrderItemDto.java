package com.nusiss.inventory.backend.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemDto {
  private Long productId;
  private String productName;
  private int quantity;
  private BigDecimal price;
}
