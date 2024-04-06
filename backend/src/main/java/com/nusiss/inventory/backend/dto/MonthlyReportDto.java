package com.nusiss.inventory.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonthlyReportDto {
  private TotalSalesDto totalSales;
  private List<TopSellingProductDto> topSellingProducts;
  private List<CategoryRevenueDto> revenueByCategory;
}
