package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dao.RoleDao;
import com.nusiss.inventory.backend.dao.UserDao;
import com.nusiss.inventory.backend.dto.LoginResDto;
import com.nusiss.inventory.backend.dto.UserDto;
import com.nusiss.inventory.backend.entity.Role;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.service.AuthenticationService;
import com.nusiss.inventory.backend.service.TokenService;
import com.nusiss.inventory.backend.utils.GlobalConstants;
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

  private final RoleDao roleDao;

  private final TokenService tokenService;

  private final UserDao userDao;

  @Autowired
  public AuthenticationServiceImpl(
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder,
      RoleDao roleDao,
      TokenService tokenService,
      UserDao userDao) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.roleDao = roleDao;
    this.tokenService = tokenService;
    this.userDao = userDao;
  }

  public LoginResDto loginUser(String username, String password) {
    try {
      Authentication auth =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(username, password));

      String token = tokenService.generateJwt(auth);

      return new LoginResDto(userDao.findByUsername(username).get().toDto(), token);
    } catch (AuthenticationException e) {
      return new LoginResDto(null, "");
    }
  }
}
