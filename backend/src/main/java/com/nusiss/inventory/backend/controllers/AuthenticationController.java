package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.LoginReqDto;
import com.nusiss.inventory.backend.dto.LoginResDto;
import com.nusiss.inventory.backend.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

  @Autowired private AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<LoginResDto> loginUser(@RequestBody LoginReqDto body) {
    LoginResDto res = authenticationService.loginUser(body.getUsername(), body.getPassword());
    if (res.getUser() != null) return ResponseEntity.ok(res);
    return ResponseEntity.status(401).build();
  }
}
