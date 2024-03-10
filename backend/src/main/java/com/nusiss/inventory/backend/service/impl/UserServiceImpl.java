package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.dto.UserDto;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  @Autowired
  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDto getUserById(Long id) {
    User user = userDao.findById(id).orElse(null);
    return user.toDto();
  }

  @Override
  public UserDto createUser(User user) {
    return userDao.saveUser(user).toDto();
  }

  @Override
  public UserDto updateUser(Long id, UserDto userDto) {
    User user = userDao.findById(id).orElse(null);
    if (user != null) {
      user.setUsername(userDto.getUsername());
      user.setEmail(userDto.getEmail());
      // update roles or other fields
      User updatedUser = userDao.saveUser(user);
      return updatedUser.toDto();
    }
    return null;
  }

  @Override
  public UserDto updateUserPassword(Long id, String encodedPassword) {
    User user = userDao.findById(id).orElse(null);
    if (user != null) {
      user.setPassword(encodedPassword);
      User updatedUser = userDao.saveUser(user);
      return updatedUser.toDto();
    }
    return null;
  }

  @Override
  public void deleteUserById(Long id) {
    userDao.deleteUserById(id);
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> users = userDao.findAllUsers();
    return users.stream().map(User::toDto).collect(Collectors.toList());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userDao
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("username is invalid"));
  }
}
