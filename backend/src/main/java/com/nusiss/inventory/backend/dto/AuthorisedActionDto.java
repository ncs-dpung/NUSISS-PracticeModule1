package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.AuthorisedAction;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorisedActionDto {
  private Long id;
  private String authority;

  public AuthorisedAction toEntity() {
    AuthorisedAction action = new AuthorisedAction();
    action.setId(id);
    action.setAuthority(authority);
    return action;
  }
}
