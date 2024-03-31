package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.dto.RoleDto;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
  private final RoleDao roleDao;
  private final ObjectMapper objectMapper;

  @Autowired
  public RoleServiceImpl(RoleDao roleDao, ObjectMapper objectMapper) {
    this.roleDao = roleDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public RoleDto getRoleByName(String name) {
    Role role = roleDao.findByName(name).orElse(null);
    if (role == null) return null;
    return role.toDto();
  }

  @Override
  public RoleDto getRoleById(Long id) {
    Role role = roleDao.findById(id).orElse(null);
    if (role == null) return null;
    return role.toDto();
  }

  @Override
  public RoleDto createRole(Role role) {
    return roleDao.saveRole(role).toDto();
  }

  @Override
  public RoleDto updateRole(Long id, RoleDto updateDto) {
    Role role =
        roleDao
            .findById(id)
            .orElseThrow(() -> new RuntimeException("role with id [" + id + "] does not exist"));
    try {
      objectMapper.updateValue(role, updateDto);
      Role updatedRole = roleDao.saveRole(role);
      return updatedRole.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteRoleById(Long id) {
    roleDao.deleteRoleById(id);
  }

  @Override
  public List<RoleDto> getAllRoles() {
    List<Role> roles = roleDao.findAllRoles();
    return roles.stream().map(Role::toDto).collect(Collectors.toList());
  }
}
