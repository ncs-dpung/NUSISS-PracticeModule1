package com.nusiss.inventory.backend.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalSalesDto {
  private int year;
  private int month;
  private long totalOrders;
  private BigDecimal totalRevenue;
  private String mostSoldProduct;
}
