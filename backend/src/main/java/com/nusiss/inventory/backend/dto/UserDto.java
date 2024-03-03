package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String email;
  private Role role;
}
