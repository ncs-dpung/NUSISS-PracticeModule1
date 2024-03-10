package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.StaffDao;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.repository.RoleRepository;
import com.nusiss.inventory.backend.repository.StaffRepository;
import com.nusiss.inventory.backend.repository.UserRepository;
import com.nusiss.inventory.backend.utils.GlobalConstants;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffDaoImpl implements StaffDao {
  private final StaffRepository staffRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public StaffDaoImpl(
      RoleRepository roleRepository,
      StaffRepository staffRepository,
      UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.staffRepository = staffRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Optional<Staff> findById(Long id) {
    return staffRepository.findById(id);
  }

  @Transactional
  @Override
  // to implement test with debugger
  public Staff saveStaff(Staff staff) {
    Role userRole = roleRepository.findByName(GlobalConstants.ROLE_USER).get();
    User user = staff.getUser();
    if (!user.getRoles().contains(userRole)) {
      user.getRoles().add(userRole);
    }
    user.setStaff(staff);
    User savedUser = userRepository.save(user);
    Staff savedStaff = staffRepository.save(staff);
    savedStaff.setUser(savedUser);
    return savedStaff;
  }

  @Override
  public void deleteStaffById(Long id) {
    staffRepository.deleteById(id);
  }

  @Override
  public List<Staff> findAllStaff() {
    return staffRepository.findAll();
  }
}
