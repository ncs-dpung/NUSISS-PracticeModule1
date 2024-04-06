package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.dto.StaffUpdateDto;
import java.util.List;

public interface StaffService {
  StaffDto getStaffById(Long id);

  StaffDto createStaff(StaffDto staffDto);

  StaffDto createAdminStaff(StaffDto staffDto);

  StaffDto updateStaff(Long id, StaffUpdateDto staffDto);

  void deleteStaffById(Long id);

  List<StaffDto> getAllStaff();
}
