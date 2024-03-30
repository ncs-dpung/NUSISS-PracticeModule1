package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.RoleDto;
import com.nusiss.inventory.backend.entity.Role;
import java.util.List;

public interface RoleService {
  RoleDto getRoleByName(String name);

  RoleDto getRoleById(Long id);

  RoleDto createRole(Role role);

  RoleDto updateRole(Long id, RoleDto roleDto);

  void deleteRoleById(Long id);

  List<RoleDto> getAllRoles();
}
