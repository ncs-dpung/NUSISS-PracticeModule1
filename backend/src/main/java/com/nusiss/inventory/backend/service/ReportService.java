package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.dto.TopSellingProductDto;
import com.nusiss.inventory.backend.dto.TotalSalesDto;

import java.util.List;

public interface ReportService {
    List<TotalSalesDto> getTotalSalesAndMostSoldProductByMonth();
    List<TopSellingProductDto> getTopSellingProducts();
    List<CategoryRevenueDto> getRevenueByCategory();
}
