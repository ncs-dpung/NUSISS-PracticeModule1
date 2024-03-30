package com.nusiss.inventory.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedPrincipalDto {
  private long userId;
  private long staffId;
}
