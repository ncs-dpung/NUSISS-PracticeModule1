package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.Role;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDto {
  private Long id;
  private String name;
  private Set<AuthorisedActionDto> actions;

  public Role toEntity() {
    Role role = new Role();
    role.setId(id);
    role.setName(name);
    if (actions != null) {
      role.setActions(
          actions.stream().map(AuthorisedActionDto::toEntity).collect(Collectors.toSet()));
    } else {
      role.setActions(new HashSet<>());
    }
    return role;
  }
}
