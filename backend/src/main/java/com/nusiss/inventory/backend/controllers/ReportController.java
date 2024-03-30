package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.dto.TotalSalesDto;
import com.nusiss.inventory.backend.service.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

  private final ReportService reportService;

  @Autowired
  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/sales-by-month")
  public ResponseEntity<List<TotalSalesDto>> getTotalSalesRevenueAndMostSoldProductByMonth() {
    return ResponseEntity.ok(reportService.getTotalSalesAndMostSoldProductByMonth());
  }

  @GetMapping("/top-selling-products")
  public ResponseEntity<List<TopSellingProductDto>> getTopSellingProducts() {
    return ResponseEntity.ok(reportService.getTopSellingProducts());
  }

  @GetMapping("/revenue-by-category")
  public ResponseEntity<List<CategoryRevenueDto>> getRevenueByCategory() {
    return ResponseEntity.ok(reportService.getRevenueByCategory());
  }
}
