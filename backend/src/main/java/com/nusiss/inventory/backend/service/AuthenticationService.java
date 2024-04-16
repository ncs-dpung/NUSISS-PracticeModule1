package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.LoginResDto;

public interface AuthenticationService {
  public LoginResDto loginUser(String username, String password);
}
