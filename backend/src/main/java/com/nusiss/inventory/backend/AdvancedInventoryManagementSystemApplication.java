package com.nusiss.inventory.backend;

import com.nusiss.inventory.backend.utils.DataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AdvancedInventoryManagementSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdvancedInventoryManagementSystemApplication.class, args);
  }

  @Bean
  public CommandLineRunner run(DataGenerator generator) {
    return args -> {
      generator.generateCreateActions();
      generator.generateReadActions();
      generator.generateUpdateActions();
      generator.generateDeleteActions();
      generator.generateBasicRoles();
      generator.generateAdminStaff();
      generator.generateSalesRepRole();
      generator.generateWarehouseMgrRole();
      generator.generateProcurementOffrRole();
    };
  }
}
