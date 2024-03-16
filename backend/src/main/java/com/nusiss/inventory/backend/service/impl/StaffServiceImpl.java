package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.StaffDao;
import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.dto.StaffUpdateDto;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.service.StaffService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
  private final StaffDao staffDao;
  private final ObjectMapper objectMapper;

  @Autowired
  public StaffServiceImpl(StaffDao staffDao, ObjectMapper objectMapper) {
    this.staffDao = staffDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public StaffDto getStaffById(Long id) {
    Staff staff = staffDao.findById(id).orElse(null);
    if (staff == null) return null;

    return staff.toDto();
  }

  @Override
  public StaffDto createStaff(StaffDto staffDto) {
    return staffDao.saveStaff(staffDto.toEntity()).toDto();
  }

  @Override
  public StaffDto updateStaff(Long id, StaffUpdateDto updateDto) {
    Staff staff =
        staffDao
            .findById(id)
            .orElseThrow(() -> new RuntimeException("staff with id [" + id + "] does not exist"));

    try {
      objectMapper.updateValue(staff, updateDto);
      Staff updatedStaff = staffDao.saveStaff(staff);
      return updatedStaff.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteStaffById(Long id) {
    staffDao.deleteStaffById(id);
  }

  @Override
  public List<StaffDto> getAllStaff() {
    List<Staff> staffs = staffDao.findAllStaff();
    return staffs.stream().map(Staff::toDto).collect(Collectors.toList());
  }
}
