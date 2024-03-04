package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.Role;
import java.util.List;
import java.util.Optional;

public interface RoleDao {
  Optional<Role> findById(Long id);

  Optional<Role> findByName(String name);

  Role saveRole(Role role);

  void deleteRoleById(Long id);

  List<Role> findAllRoles();
}
