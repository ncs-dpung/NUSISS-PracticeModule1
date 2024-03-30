package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.dto.MonthlyReportDto;
import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.dto.TotalSalesDto;
import com.nusiss.inventory.backend.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "Reports")
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @ApiOperation(value = "Monthly Report", response = MonthlyReportDto.class, notes = "Get monthly report for the given year and month")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved monthly report"),
    })
    @GetMapping("/monthly-report")
    public ResponseEntity<MonthlyReportDto> getMonthlyReport(@RequestParam int year, @RequestParam int month) {
        MonthlyReportDto monthlyReport = reportService.getMonthlyReport(year, month);
        return ResponseEntity.ok(monthlyReport);
    }

//    @GetMapping("/sales-by-month")
//    public ResponseEntity<List<TotalSalesDto>> getTotalSalesRevenueAndMostSoldProductByMonth() {
//        return ResponseEntity.ok(reportService.getTotalSalesAndMostSoldProductByMonth());
//    }
//
//    @GetMapping("/top-selling-products")
//    public ResponseEntity<List<TopSellingProductDto>> getTopSellingProducts() {
//        return ResponseEntity.ok(reportService.getTopSellingProducts());
//    }
//
//    @GetMapping("/revenue-by-category")
//    public ResponseEntity<List<CategoryRevenueDto>> getRevenueByCategory() {
//        return ResponseEntity.ok(reportService.getRevenueByCategory());
//    }
}
