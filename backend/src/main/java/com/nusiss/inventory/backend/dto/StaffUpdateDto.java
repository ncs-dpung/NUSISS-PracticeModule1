package com.nusiss.inventory.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffUpdateDto {
  private String firstName;
  private String lastName;
  private UserUpdateDto userUpdateDto;
}
