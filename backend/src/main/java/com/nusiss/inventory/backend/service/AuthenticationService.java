package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.LoginResDto;
import com.nusiss.inventory.backend.dto.UserDto;

public interface AuthenticationService {

  public UserDto registerUser(String username, String password, String email);

  public LoginResDto loginUser(String username, String password);
}
