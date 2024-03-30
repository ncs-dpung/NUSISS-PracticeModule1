package com.nusiss.inventory.backend.utils;

import com.nusiss.inventory.backend.dao.StaffDao;
import com.nusiss.inventory.backend.dto.AuthorisedActionDto;
import com.nusiss.inventory.backend.entity.AuthorisedAction;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.service.AuthorisedActionService;
import com.nusiss.inventory.backend.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DataGenerator {
  private final RoleService roleService;
  private final AuthorisedActionService actionService;
  private final StaffDao staffDao;
  private final PasswordEncoder passwordEncoder;

  public DataGenerator(
      RoleService roleService,
      AuthorisedActionService actionService,
      StaffDao staffDao,
      PasswordEncoder passwordEncoder) {
    this.actionService = actionService;
    this.roleService = roleService;
    this.staffDao = staffDao;
    this.passwordEncoder = passwordEncoder;
  }

  private void saveActions(String[] authorities) {
    for (String authority : authorities) {
      AuthorisedActionDto dto = actionService.getAuthorisedActionByAuthority(authority);
      if (dto == null) {
        AuthorisedAction entity = new AuthorisedAction(authority);
        actionService.createAuthorisedAction(entity);
      }
    }
  }

  public void generateCreateActions() {
    String[] authorities = {
      GlobalConstants.AUT_CREATE_STAFF,
      GlobalConstants.AUT_CREATE_ROLE,
      GlobalConstants.AUT_CREATE_ACTION,
    };
    this.saveActions(authorities);
  }

  public void generateReadActions() {
    String[] authorities = {
      GlobalConstants.AUT_READ_STAFF,
      GlobalConstants.AUT_READ_ROLE,
      GlobalConstants.AUT_READ_ACTION,
    };
    this.saveActions(authorities);
  }

  public void generateUpdateActions() {
    String[] authorities = {
      GlobalConstants.AUT_UPDATE_STAFF,
      GlobalConstants.AUT_UPDATE_ROLE,
      GlobalConstants.AUT_UPDATE_ACTION,
    };
    this.saveActions(authorities);
  }

  public void generateDeleteActions() {
    String[] authorities = {
      GlobalConstants.AUT_DELETE_STAFF,
      GlobalConstants.AUT_DELETE_ROLE,
      GlobalConstants.AUT_DELETE_ACTION,
    };
    this.saveActions(authorities);
  }

  public void generateBasicRoles() {
    if (roleService.getRoleByName(GlobalConstants.ROLE_ADMIN) != null) return;

    Role userRole = new Role(GlobalConstants.ROLE_USER);
    roleService.createRole(userRole);
    Role adminRole = new Role(GlobalConstants.ROLE_ADMIN);
    roleService.createRole(adminRole);
  }

  public void generateAdminStaff() {
    if (staffDao.findById(1L).isPresent()) return;
    User admin = new User();
    admin.setEmail("admin@email.com");
    admin.setUsername("admin");
    admin.setPassword(passwordEncoder.encode("password"));

    Staff adminStaff = new Staff();
    adminStaff.setFirstName("admin");
    adminStaff.setLastName("admin");
    adminStaff.setUser(admin);
    staffDao.saveAdminStaff(adminStaff);
  }
}
