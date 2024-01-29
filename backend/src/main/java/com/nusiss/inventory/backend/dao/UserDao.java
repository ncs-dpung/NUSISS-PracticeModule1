package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);
    User findByUsername(String username);
    User saveUser(User user);
    void deleteUserById(Long id);
    List<User> findAllUsers();
}
