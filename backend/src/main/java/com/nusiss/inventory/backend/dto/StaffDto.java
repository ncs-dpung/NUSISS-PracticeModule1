package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffDto {
  private Long id;
  private String firstName;
  private String lastName;
  private UserDto user;

  public Staff toEntity() {
    Staff staff = new Staff();
    staff.setId(id);
    staff.setFirstName(firstName);
    staff.setLastName(lastName);
    staff.setUser(user.toEntity());
    return staff;
  }
}
