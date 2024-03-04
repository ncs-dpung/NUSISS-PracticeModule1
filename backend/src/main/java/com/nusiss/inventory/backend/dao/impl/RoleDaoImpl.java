package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.repository.RoleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDaoImpl implements RoleDao {
  @Autowired private RoleRepository roleRepository;

  @Override
  public Optional<Role> findById(Long id) {
    return roleRepository.findById(id);
  }

  @Override
  public Optional<Role> findByName(String name) {
    return roleRepository.findByName(name);
  }

  @Override
  public Role saveRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public void deleteRoleById(Long id) {
    roleRepository.deleteById(id);
    return;
  }

  @Override
  public List<Role> findAllRoles() {
    return roleRepository.findAll();
  }
}
