package com.nusiss.inventory.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateDto {
  private String oldPassword;
  private String newPassword;
}
