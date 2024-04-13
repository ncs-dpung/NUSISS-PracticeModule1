package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.dto.LoginResDto;
import com.nusiss.inventory.backend.service.AuthenticationService;
import com.nusiss.inventory.backend.service.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final PasswordEncoder passwordEncoder;

  private final TokenService tokenService;

  @Autowired
  public AuthenticationServiceImpl(
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.tokenService = tokenService;
  }

  public LoginResDto loginUser(String username, String password) {
    try {
      Authentication auth =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(username, password));

      String token = tokenService.generateJwt(auth);
      User user = (User) auth.getPrincipal();

      return new LoginResDto(user.toDto(), token);
    } catch (AuthenticationException e) {
      return new LoginResDto(null, "");
    }
  }
}
