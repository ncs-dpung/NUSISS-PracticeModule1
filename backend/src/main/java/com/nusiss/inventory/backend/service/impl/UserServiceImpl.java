package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.dto.UserDto;
import com.nusiss.inventory.backend.dto.UserPasswordUpdateDto;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserDao userDao;
  private final PasswordEncoder passwordEncoder;
  private final ObjectMapper objectMapper;

  @Autowired
  public UserServiceImpl(
      UserDao userDao, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.objectMapper = objectMapper;
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
  public UserDto updateUser(Long id, UserDto updateDto) {
    User user =
        userDao
            .findById(id)
            .orElseThrow(() -> new RuntimeException("user with id [" + id + "] does not exist"));
    try {
      objectMapper.updateValue(user, updateDto);
      User updatedUser = userDao.saveUser(user);
      return updatedUser.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  @Transactional
  public UserDto updateUserPassword(Long id, UserPasswordUpdateDto updateDto) {
    User user = userDao.findById(id).orElse(null);
    if (user == null) throw new RuntimeException("user with id [" + id + "] not found");
    if (user.getPassword() != null
        && !passwordEncoder.matches(updateDto.getOldPassword(), user.getPassword())) {
      throw new RuntimeException("old password does not match");
    }
    user.setPassword(passwordEncoder.encode(updateDto.getNewPassword()));
    User updatedUser = userDao.saveUser(user);
    return updatedUser.toDto();
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
