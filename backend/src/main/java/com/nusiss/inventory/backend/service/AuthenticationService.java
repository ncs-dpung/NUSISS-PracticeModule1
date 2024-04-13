package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.LoginResDto;
import com.nusiss.inventory.backend.dto.UserDto;

public interface AuthenticationService {
  public LoginResDto loginUser(String username, String password);
}
