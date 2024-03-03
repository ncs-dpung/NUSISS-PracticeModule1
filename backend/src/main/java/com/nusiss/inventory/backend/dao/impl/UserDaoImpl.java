package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {
  @Autowired private UserRepository userRepository;

  @Override
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User saveUser(User user) {
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
