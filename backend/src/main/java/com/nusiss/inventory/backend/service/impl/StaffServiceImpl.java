package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.dao.StaffDao;
import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.service.StaffService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {
  private final StaffDao staffDao;
  private final UserDao userDao;
  private final RoleDao roleDao;

  @Autowired
  public StaffServiceImpl(StaffDao staffDao, UserDao userDao, RoleDao roleDao) {
    this.staffDao = staffDao;
    this.userDao = userDao;
    this.roleDao = roleDao;
  }

  @Override
  public StaffDto getStaffById(Long id) {
    Staff staff = staffDao.findById(id).orElse(null);
    return staff.toDto();
  }

  @Override
  public StaffDto createStaff(StaffDto staffDto) {
    return staffDao.saveStaff(staffDto.toEntity()).toDto();
  }

  @Override
  public StaffDto updateStaff(Long id, StaffDto staffDto) {
    Staff staff = staffDao.findById(id).orElse(null);
    if (staff != null) {
      staff.setId(staffDto.getId());
      staff.setFirstName(staffDto.getFirstName());
      staff.setLastName(staffDto.getLastName());
      staff.setUser(staffDto.getUser().toEntity());
      // update roles or other fields
      Staff updatedStaff = staffDao.saveStaff(staff);
      return updatedStaff.toDto();
    }
    return null;
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
