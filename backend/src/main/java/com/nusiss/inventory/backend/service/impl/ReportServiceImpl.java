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


//    public List<TotalSalesDto> getTotalSalesAndMostSoldProductByMonth(int year, int month) {
//        List<Object[]> monthlySales = orderRepository.findTotalSalesAndRevenueByMonth();
//        List<TotalSalesDto> results = new ArrayList<>();
//
//        System.out.println("monthlySales: " + orderRepository.findTotalSalesAndRevenueByMonth());
//
//
//        for (Object[] monthData : monthlySales) {
//            int year = ((Number) monthData[0]).intValue();
//            int month = ((Number) monthData[1]).intValue();
//            long totalOrders = ((Number) monthData[2]).longValue();
//            BigDecimal totalRevenue = (BigDecimal) monthData[3];
//
//            List<Object[]> mostSoldProductData = orderRepository.findMostSoldProductForMonthAndYear(String.valueOf(year), String.valueOf(month));
//
//            String mostSoldProductName = null;
//            for(Object[] data: mostSoldProductData){
//                mostSoldProductName = (String) data[0];
//            }
//
//            TotalSalesDto dto = new TotalSalesDto(year, month, totalOrders, totalRevenue, mostSoldProductName);
//            results.add(dto);
//        }
//
//        return results;
//    }
//
//
//
//    public List<TopSellingProductDto> getTopSellingProducts(int year, int month) {
//        return productRepository.findTopSellingProducts();
//    }
//
//
//    public List<CategoryRevenueDto> getRevenueByCategory(int year, int month) {
//        return categoryRepository.findRevenueByCategory();
//    }
}

