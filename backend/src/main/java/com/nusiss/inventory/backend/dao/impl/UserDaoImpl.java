package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements UserDao {
  @Autowired private UserRepository userRepository;

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public User findByUsername(String username) {
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
