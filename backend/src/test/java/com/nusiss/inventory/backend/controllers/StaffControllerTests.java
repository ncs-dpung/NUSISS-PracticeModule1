package com.nusiss.inventory.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nusiss.inventory.backend.dto.StaffDto;
import com.nusiss.inventory.backend.dto.StaffRegResDto;
import com.nusiss.inventory.backend.dto.StaffUpdateDto;
import com.nusiss.inventory.backend.entity.Staff;
import com.nusiss.inventory.backend.entity.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class StaffControllerTests {

  @Autowired private StaffController staffController;

  @Test
  public void testGetStaff() {
    ResponseEntity<List<StaffDto>> response = staffController.getAllStaff();
    assertEquals(response.getStatusCodeValue(), 200);
    assertEquals(response.getBody().size(), 0);
  }

  @Test
  public void testCreateUpdateDeleteStaff() {
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

    ResponseEntity<List<StaffDto>> listResponse = staffController.getAllStaff();
    assertEquals(listResponse.getStatusCodeValue(), 200);
    assertNotNull(listResponse.getBody());
    assertEquals(listResponse.getBody().size(), 1);

    Staff staff = listResponse.getBody().get(0).toEntity();
    String newFirstName = "Buck";
    StaffUpdateDto updates = new StaffUpdateDto();
    updates.setFirstName(newFirstName);
    ResponseEntity<StaffDto> updResponse = staffController.updateStaff(staff.getId(), updates);
    assertEquals(updResponse.getStatusCodeValue(), 200);
    assertNotNull(updResponse.getBody());
    assertEquals(updResponse.getBody().getFirstName(), newFirstName);

    ResponseEntity response = staffController.deleteStaff(staff.getId());
    assertEquals(response.getStatusCodeValue(), 204);
  }
}
