package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.service.TokenService;
import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

  @Autowired JwtEncoder jwtEncoder;

  @Autowired JwtDecoder jwtDecoder;

  public String generateJwt(Authentication auth) {
    Instant now = Instant.now();

    String scope =
        auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .subject(auth.getName())
            .claim("actions", scope)
            .build();

    String encoded = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    return encoded;
  }
}
