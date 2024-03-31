package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.dto.MonthlyReportDto;
import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.dto.TotalSalesDto;
import com.nusiss.inventory.backend.repository.CategoryRepository;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ReportServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public MonthlyReportDto getMonthlyReport(int year, int month) {
        MonthlyReportDto monthlyReport = new MonthlyReportDto();

        // Fetch Total Sales for the specific month and year
        List<Object[]> monthlySalesData = orderRepository.findTotalSalesAndRevenueForSpecificMonth(year, month);
        TotalSalesDto totalSalesDto = monthlySalesData.stream().map(data -> new TotalSalesDto(
                year,
                month,
                ((Number) data[2]).longValue(),
                (BigDecimal) data[3],
                // For most sold product, populate this next
                ""
        )).findFirst().orElse(new TotalSalesDto(year, month, 0L, BigDecimal.ZERO, ""));

        // Fetch name of the most sold product for the given month and year
        List<Object[]> mostSoldProductData = orderRepository.findMostSoldProductForMonthAndYear(String.valueOf(year), String.valueOf(month));
        if (!mostSoldProductData.isEmpty()) {
            Object[] data = mostSoldProductData.get(0);
            String mostSoldProductName = (String) data[0];
            totalSalesDto.setMostSoldProduct(mostSoldProductName);
        }

        monthlyReport.setTotalSales(totalSalesDto);

        // Fetch Top Selling Products
        List<Object[]> rawResults = productRepository.findTopSellingProducts(year, month);
        monthlyReport.setTopSellingProducts(getTopSellingProducts(rawResults));

        // Fetch Revenue by Category
        List<CategoryRevenueDto> revenueByCategory = categoryRepository.findRevenueByCategory(year, month);
        monthlyReport.setRevenueByCategory(revenueByCategory);

        return monthlyReport;
    }

    private List<TopSellingProductDto> getTopSellingProducts(List<Object[]> rawResults) {
        return rawResults.stream().map(data -> new TopSellingProductDto(
                (String) data[0],
                ((Number) data[1]).longValue()
        )).toList();
    }
}

