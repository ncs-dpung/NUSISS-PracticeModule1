package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.MonthlyReportDto;

public interface ReportService {
    //List<TotalSalesDto> getTotalSalesAndMostSoldProductByMonth(); // Fetch total sales and revenue by month (input: year, month)
    //List<TopSellingProductDto> getTopSellingProducts(); // Fetch top selling products  (input: year, month)
    //List<CategoryRevenueDto> getRevenueByCategory(); // Fetch revenue by category (input: year, month)

    MonthlyReportDto getMonthlyReport(int year, int month);
}
