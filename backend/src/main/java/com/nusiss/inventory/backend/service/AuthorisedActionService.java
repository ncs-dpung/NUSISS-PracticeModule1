package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.AuthorisedActionDto;
import com.nusiss.inventory.backend.entity.AuthorisedAction;
import java.util.List;

public interface AuthorisedActionService {
  AuthorisedActionDto getAuthorisedActionByAuthority(String authority);

  AuthorisedActionDto getAuthorisedActionById(Long id);

  AuthorisedActionDto createAuthorisedAction(AuthorisedAction action);

  AuthorisedActionDto updateAuthorisedAction(Long id, AuthorisedActionDto actionDto);

  void deleteAuthorisedActionById(Long id);

  List<AuthorisedActionDto> getAllAuthorisedActions();
}
