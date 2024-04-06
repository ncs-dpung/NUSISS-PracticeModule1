package com.nusiss.inventory.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffRegResDto {
  private String password;
  private StaffDto staff;
}
