package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.repository.RoleRepository;
import com.nusiss.inventory.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public UserDaoImpl(RoleRepository roleRepository, UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Transactional
  @Override
  public User saveUser(User user) {
    Collection<Role> roles = user.getRoles();
    roles.stream().map(role -> role.getUsers().add(user));
    roleRepository.saveAll(roles);
    return userRepository.save(user);
  }

  @Override
  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }
}
