package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto getUserById(Long id);

  UserDto createUser(UserDto userDto);

  UserDto updateUser(Long id, UserDto userDto);

  void deleteUserById(Long id);

  List<UserDto> getAllUsers();
}
