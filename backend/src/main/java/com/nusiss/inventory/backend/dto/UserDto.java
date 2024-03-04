package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.User;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
  private Long id;
  private String username;
  private String email;
  private Set<RoleDto> roles;

  public User toEntity() {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    user.setEmail(email);
    user.setRoles(roles.stream().map(RoleDto::toEntity).collect(Collectors.toSet()));
    return user;
  }
}
