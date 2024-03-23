package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.service.TokenService;
import java.time.Instant;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
  @Value("${app.security.jwt.token.validity.s:3600}")
  private long JWT_TOKEN_VALIDITY_S;

  @Autowired JwtEncoder jwtEncoder;

  @Autowired JwtDecoder jwtDecoder;

  public String generateJwt(Authentication authentication) {
    String username = authentication.getName();
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    Instant now = Instant.now();
    Instant expiry = Instant.now().plusSeconds(JWT_TOKEN_VALIDITY_S);
    JwtClaimsSet.Builder builder =
        JwtClaimsSet.builder().issuedAt(now).expiresAt(expiry).subject(username);
    for (GrantedAuthority authority : authorities) {
      builder.claim(authority.getAuthority(), true);
    }

    JwtClaimsSet claims = builder.build();
    String encoded = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    return encoded;
  }
}
