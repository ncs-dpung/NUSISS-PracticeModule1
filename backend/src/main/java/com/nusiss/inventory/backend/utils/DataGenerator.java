package com.nusiss.inventory.backend.utils;

import com.nusiss.inventory.backend.dao.StaffDao;
import com.nusiss.inventory.backend.dto.AuthorisedActionDto;
import com.nusiss.inventory.backend.entity.AuthorisedAction;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.service.AuthorisedActionService;
import com.nusiss.inventory.backend.service.RoleService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DataGenerator {
  private final RoleService roleService;
  private final AuthorisedActionService actionService;
  private final StaffDao staffDao;
  private final PasswordEncoder passwordEncoder;
  private final EntityManager entityManager;

  public DataGenerator(
      RoleService roleService,
      AuthorisedActionService actionService,
      EntityManager entityManager,
      StaffDao staffDao,
      PasswordEncoder passwordEncoder) {
    this.actionService = actionService;
    this.entityManager = entityManager;
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
      GlobalConstants.AUT_CREATE_CUSTOMER,
      GlobalConstants.AUT_CREATE_SUPPLIER,
      GlobalConstants.AUT_CREATE_ORDER,
      GlobalConstants.AUT_CREATE_DELIVERY,
      GlobalConstants.AUT_CREATE_INVENTORY,
    };
    this.saveActions(authorities);
  }

  public void generateReadActions() {
    String[] authorities = {
      GlobalConstants.AUT_READ_STAFF,
      GlobalConstants.AUT_READ_ROLE,
      GlobalConstants.AUT_READ_ACTION,
      GlobalConstants.AUT_READ_CUSTOMER,
      GlobalConstants.AUT_READ_SUPPLIER,
      GlobalConstants.AUT_READ_ORDER,
      GlobalConstants.AUT_READ_DELIVERY,
      GlobalConstants.AUT_READ_INVENTORY,
    };
    this.saveActions(authorities);
  }

  public void generateUpdateActions() {
    String[] authorities = {
      GlobalConstants.AUT_UPDATE_STAFF,
      GlobalConstants.AUT_UPDATE_ROLE,
      GlobalConstants.AUT_UPDATE_ACTION,
      GlobalConstants.AUT_UPDATE_CUSTOMER,
      GlobalConstants.AUT_UPDATE_SUPPLIER,
      GlobalConstants.AUT_UPDATE_ORDER,
      GlobalConstants.AUT_UPDATE_DELIVERY,
      GlobalConstants.AUT_UPDATE_INVENTORY,
    };
    this.saveActions(authorities);
  }

  public void generateDeleteActions() {
    String[] authorities = {
      GlobalConstants.AUT_DELETE_STAFF,
      GlobalConstants.AUT_DELETE_ROLE,
      GlobalConstants.AUT_DELETE_ACTION,
      GlobalConstants.AUT_DELETE_CUSTOMER,
      GlobalConstants.AUT_DELETE_SUPPLIER,
      GlobalConstants.AUT_DELETE_ORDER,
      GlobalConstants.AUT_DELETE_DELIVERY,
      GlobalConstants.AUT_DELETE_INVENTORY,
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

  private Set<AuthorisedAction> getAuthorisedActions(String[] authorities) {
    Set<AuthorisedAction> actions = new HashSet<>();
    for (String authority : authorities) {
      AuthorisedActionDto dto = actionService.getAuthorisedActionByAuthority(authority);
      if (dto != null) {
        AuthorisedAction entity = dto.toEntity();
        if (!entityManager.contains(entity)) {
          entity = entityManager.merge(entity);
        }
        actions.add(entity);
      }
    }
    return actions;
  }

  public void generateSalesRepRole() {
    final String SALES_REP = "SALES_REP";
    if (roleService.getRoleByName(SALES_REP) != null) return;
    String[] actions = {
      GlobalConstants.AUT_READ_INVENTORY,
      GlobalConstants.AUT_READ_ORDER,
      GlobalConstants.AUT_CREATE_ORDER,
      GlobalConstants.AUT_UPDATE_ORDER,
      GlobalConstants.AUT_READ_DELIVERY,
      GlobalConstants.AUT_CREATE_DELIVERY,
      GlobalConstants.AUT_UPDATE_DELIVERY,
      GlobalConstants.AUT_READ_CUSTOMER,
      GlobalConstants.AUT_CREATE_CUSTOMER,
      GlobalConstants.AUT_UPDATE_CUSTOMER,
    };
    Role salesRole = new Role(SALES_REP);
    salesRole.setActions(getAuthorisedActions(actions));
    roleService.createRole(salesRole);
  }

  public void generateWarehouseMgrRole() {
    final String WAREHOUSE_MGR = "WAREHOUSE_MGR";
    if (roleService.getRoleByName(WAREHOUSE_MGR) != null) return;
    String[] actions = {
      GlobalConstants.AUT_READ_INVENTORY,
      GlobalConstants.AUT_CREATE_INVENTORY,
      GlobalConstants.AUT_UPDATE_INVENTORY,
      GlobalConstants.AUT_READ_ORDER,
      GlobalConstants.AUT_READ_DELIVERY,
      GlobalConstants.AUT_CREATE_DELIVERY,
      GlobalConstants.AUT_UPDATE_DELIVERY,
    };
    Role warehouseRole = new Role(WAREHOUSE_MGR);
    warehouseRole.setActions(getAuthorisedActions(actions));
    roleService.createRole(warehouseRole);
  }

  public void generateProcurementOffrRole() {
    final String PROCUREMENT_OFFR = "PROCUREMENT_OFFR";
    if (roleService.getRoleByName(PROCUREMENT_OFFR) != null) return;
    String[] actions = {
      GlobalConstants.AUT_READ_INVENTORY,
      GlobalConstants.AUT_CREATE_INVENTORY,
      GlobalConstants.AUT_UPDATE_INVENTORY,
      GlobalConstants.AUT_READ_ORDER,
      GlobalConstants.AUT_CREATE_ORDER,
      GlobalConstants.AUT_UPDATE_ORDER,
      GlobalConstants.AUT_READ_DELIVERY,
      GlobalConstants.AUT_CREATE_DELIVERY,
      GlobalConstants.AUT_UPDATE_DELIVERY,
      GlobalConstants.AUT_READ_SUPPLIER,
      GlobalConstants.AUT_CREATE_SUPPLIER,
      GlobalConstants.AUT_UPDATE_SUPPLIER,
    };
    Role procurementRole = new Role(PROCUREMENT_OFFR);
    procurementRole.setActions(getAuthorisedActions(actions));
    roleService.createRole(procurementRole);
  }
}
