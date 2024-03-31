package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.MonthlyReportDto;

public interface ReportService {
  MonthlyReportDto getMonthlyReport(int year, int month);
}
