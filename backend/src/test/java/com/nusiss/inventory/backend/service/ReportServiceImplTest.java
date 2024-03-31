package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.CategoryRevenueDto;
import com.nusiss.inventory.backend.dto.MonthlyReportDto;
import com.nusiss.inventory.backend.repository.CategoryRepository;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.impl.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getMonthlyReportShouldReturnCorrectData() {
        int year = 2024;
        int month = 3;


        when(orderRepository.findTotalSalesAndRevenueForSpecificMonth(year, month))
                .thenReturn(List.of(
                        new Object[]{year, month, 10L, new BigDecimal("1000")},
                        new Object[]{year, month, 5L, new BigDecimal("500")}
                ));


        Object[] mockProduct1 = new Object[]{"Mock Product 1", 100L};
        Object[] mockProduct2 = new Object[]{"Mock Product 2", 90L};
        List<Object[]> mockTopSellingProducts = Arrays.asList(mockProduct1, mockProduct2);

        String mostSoldProductName = "Mock Product";
        Long totalQuantitySold = 150L;
        when(orderRepository.findMostSoldProductForMonthAndYear(String.valueOf(year), String.valueOf(month)))
                .thenReturn(List.of(new Object[]{mostSoldProductName, totalQuantitySold}, new Object[]{mostSoldProductName, totalQuantitySold}));


        when(productRepository.findTopSellingProducts(year, month)).thenReturn(mockTopSellingProducts);

        when(categoryRepository.findRevenueByCategory(year, month))
                .thenReturn(Arrays.asList(new CategoryRevenueDto("Mock Category 1", new BigDecimal("500")),
                        new CategoryRevenueDto("Mock Category 2", new BigDecimal("500"))));

        // Execute the service method
        MonthlyReportDto report = reportService.getMonthlyReport(year, month);

        // Verify the results
        assertEquals(year, report.getTotalSales().getYear());
        assertEquals(month, report.getTotalSales().getMonth());
        assertEquals(10L, report.getTotalSales().getTotalOrders());
        assertEquals(new BigDecimal("1000"), report.getTotalSales().getTotalRevenue());
        assertEquals("Mock Product", report.getTotalSales().getMostSoldProduct());

        assertEquals(2, report.getTopSellingProducts().size());
        assertEquals("Mock Product 1", report.getTopSellingProducts().get(0).getProductName());
        assertEquals(100L, report.getTopSellingProducts().get(0).getTotalQuantity());

        assertEquals(2, report.getRevenueByCategory().size());
        assertEquals("Mock Category 1", report.getRevenueByCategory().get(0).getCategoryName());
        assertEquals(new BigDecimal("500"), report.getRevenueByCategory().get(0).getRevenue());
    }
}
