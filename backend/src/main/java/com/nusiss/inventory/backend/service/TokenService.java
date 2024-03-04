package com.nusiss.inventory.backend.service;

import org.springframework.security.core.Authentication;

public interface TokenService {
  public String generateJwt(Authentication auth);
}
