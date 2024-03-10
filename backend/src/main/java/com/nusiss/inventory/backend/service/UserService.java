package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.UserDto;
import com.nusiss.inventory.backend.entity.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto getUserById(Long id);

  UserDto createUser(User user);

  UserDto updateUser(Long id, UserDto userDto);

  UserDto updateUserPassword(Long id, String encodedPassword);

  void deleteUserById(Long id);

  List<UserDto> getAllUsers();
}
