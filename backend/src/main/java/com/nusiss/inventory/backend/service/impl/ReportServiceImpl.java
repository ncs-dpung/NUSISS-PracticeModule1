package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.dto.TotalSalesDto;
import com.nusiss.inventory.backend.repository.CategoryRepository;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.ReportService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Autowired
  public ReportServiceImpl(
      OrderRepository orderRepository,
      ProductRepository productRepository,
      CategoryRepository categoryRepository) {
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<TotalSalesDto> getTotalSalesAndMostSoldProductByMonth() {
    List<Object[]> monthlySales = orderRepository.findTotalSalesAndRevenueByMonth();
    List<TotalSalesDto> results = new ArrayList<>();

    System.out.println("monthlySales: " + orderRepository.findTotalSalesAndRevenueByMonth());

    for (Object[] monthData : monthlySales) {
      int year = ((Number) monthData[0]).intValue();
      int month = ((Number) monthData[1]).intValue();
      long totalOrders = ((Number) monthData[2]).longValue();
      BigDecimal totalRevenue = (BigDecimal) monthData[3];

      List<Object[]> mostSoldProductData =
          orderRepository.findMostSoldProductForMonthAndYear(
              String.valueOf(year), String.valueOf(month));

      String mostSoldProductName = null;
      for (Object[] data : mostSoldProductData) {
        mostSoldProductName = (String) data[0];
      }

      TotalSalesDto dto =
          new TotalSalesDto(year, month, totalOrders, totalRevenue, mostSoldProductName);
      results.add(dto);
    }

    return results;
  }

  @Override
  public List<TopSellingProductDto> getTopSellingProducts() {
    return productRepository.findTopSellingProducts();
  }

  @Override
  public List<CategoryRevenueDto> getRevenueByCategory() {

    return categoryRepository.findRevenueByCategory();
  }
}
