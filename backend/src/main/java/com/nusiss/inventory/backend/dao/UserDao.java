package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  User saveUser(User user);

  void deleteUserById(Long id);

  List<User> findAllUsers();
}
