package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.entity.AuthorisedAction;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.repository.AuthorisedActionRepository;
import com.nusiss.inventory.backend.repository.RoleRepository;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDaoImpl implements RoleDao {
  private final RoleRepository roleRepository;
  private final AuthorisedActionRepository authorisedActionRepository;

  @Autowired
  public RoleDaoImpl(
      RoleRepository roleRepository, AuthorisedActionRepository authorisedActionRepository) {
    this.roleRepository = roleRepository;
    this.authorisedActionRepository = authorisedActionRepository;
  }

  @Override
  public Optional<Role> findById(Long id) {
    return roleRepository.findById(id);
  }

  @Override
  public Optional<Role> findByName(String name) {
    return roleRepository.findByName(name);
  }

  @Transactional
  @Override
  public Role saveRole(Role role) {
    Collection<AuthorisedAction> actions = role.getActions();
    actions.stream().map(action -> action.getRoles().add(role));
    authorisedActionRepository.saveAll(actions);
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
