package com.nusiss.inventory.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.dto.StaffRegResDto;
import com.nusiss.inventory.backend.dto.StaffUpdateDto;
import com.nusiss.inventory.backend.dto.UserPasswordUpdateDto;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class StaffControllerTests {

 @Autowired private StaffController staffController;
 private StaffRegResDto registeredStaffDto;

 @BeforeEach
 public void createStaff() {
   Staff toCreate = new Staff();
   toCreate.setFirstName("John");
   toCreate.setLastName("Doe");
   User user = new User();
   user.setEmail("john.doe@email.com");
   user.setUsername("john.doe");
   toCreate.setUser(user);

   ResponseEntity<StaffRegResDto> regResponse = staffController.createStaff(toCreate.toDto());
   assertEquals(regResponse.getStatusCodeValue(), 200);
   assertNotNull(regResponse.getBody());
   assertNotNull(regResponse.getBody().getPassword());
   this.registeredStaffDto = regResponse.getBody();
 }

 @Test
 public void testGetStaff() {
   ResponseEntity<List<StaffDto>> response = staffController.getAllStaff();
   assertEquals(response.getStatusCodeValue(), 200);
   assertEquals(response.getBody().size(), 1);
 }

 @Test
 public void testUpdateStaff() {
   StaffDto staff = registeredStaffDto.getStaff();
   String newFirstName = "Buck";
   StaffUpdateDto updates = new StaffUpdateDto();
   updates.setFirstName(newFirstName);
   ResponseEntity<StaffDto> updResponse = staffController.updateStaff(staff.getId(), updates);
   assertEquals(updResponse.getStatusCodeValue(), 200);
   assertNotNull(updResponse.getBody());
   assertEquals(updResponse.getBody().getFirstName(), newFirstName);
 }

 @Test
 public void testDeleteStaff() {
   StaffDto staff = registeredStaffDto.getStaff();
   ResponseEntity response = staffController.deleteStaff(staff.getId());
   assertEquals(response.getStatusCodeValue(), 204);
 }

 @Test
 public void testUpdateStaffPassword() {
   StaffDto staff = registeredStaffDto.getStaff();
   String newPassword = "newpassword";
   UserPasswordUpdateDto updateDto =
       new UserPasswordUpdateDto(registeredStaffDto.getPassword(), newPassword);
   ResponseEntity response = staffController.updateStaffPassword(staff.getId(), updateDto);
   assertEquals(response.getStatusCodeValue(), 200);
 }
}
