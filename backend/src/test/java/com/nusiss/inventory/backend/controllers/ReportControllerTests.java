package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.MonthlyReportDto;
import com.nusiss.inventory.backend.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTests {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    @BeforeEach
    public void setUp() {
        // This is automatically done by MockitoExtension
    }

    @Test
    public void getMonthlyReport_ReturnsMonthlyReportDto() {
        int year = 2024;
        int month = 3;
        MonthlyReportDto mockMonthlyReport = new MonthlyReportDto(); // Populate this with mock data

        when(reportService.getMonthlyReport(year, month)).thenReturn(mockMonthlyReport);

        ResponseEntity<MonthlyReportDto> response = reportController.getMonthlyReport(year, month);

        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(response.getBody(), mockMonthlyReport);
    }
}
