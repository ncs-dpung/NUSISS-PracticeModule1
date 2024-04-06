package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.Staff;
import java.util.List;
import java.util.Optional;

public interface StaffDao {
  Optional<Staff> findById(Long id);

  Staff saveStaff(Staff user);

  Staff saveAdminStaff(Staff user);

  void deleteStaffById(Long id);

  List<Staff> findAllStaff();
}
