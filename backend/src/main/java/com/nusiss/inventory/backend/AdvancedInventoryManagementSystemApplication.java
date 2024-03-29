package com.nusiss.inventory.backend;

import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.dao.StaffDao;
import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.utils.GlobalConstants;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

// @EnableScheduling
@SpringBootApplication
public class AdvancedInventoryManagementSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdvancedInventoryManagementSystemApplication.class, args);
  }

  /**
   * Generates Admin credentials once
   *
   * @param roleDao
   * @param staffDao
   * @param userDao
   * @param passwordEncoder
   * @return
   */
  @Bean
  public CommandLineRunner run(
      RoleDao roleDao, StaffDao staffDao, UserDao userDao, PasswordEncoder passwordEncoder) {
    return args -> {
      if (roleDao.findByName(GlobalConstants.ROLE_ADMIN).isPresent()) return;

      Role userRole = new Role(GlobalConstants.ROLE_USER);
      roleDao.saveRole(userRole);

      Role adminRole = new Role(GlobalConstants.ROLE_ADMIN);
      Set<Role> roles = new HashSet<>();
      roles.add(adminRole);

      User admin = new User();
      admin.setEmail("admin@email.com");
      admin.setUsername("admin");
      admin.setPassword(passwordEncoder.encode("password"));
      admin.getRoles().add(adminRole);

      Staff adminStaff = new Staff();
      adminStaff.setFirstName("admin");
      adminStaff.setLastName("admin");
      adminStaff.setUser(admin);
      staffDao.saveStaff(adminStaff);
    };
  }
}
