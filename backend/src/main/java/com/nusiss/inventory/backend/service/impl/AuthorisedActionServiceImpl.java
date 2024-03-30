package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.AuthorisedActionDao;
import com.nusiss.inventory.backend.dto.AuthorisedActionDto;
import com.nusiss.inventory.backend.entity.AuthorisedAction;
import com.nusiss.inventory.backend.service.AuthorisedActionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorisedActionServiceImpl implements AuthorisedActionService {
  private final AuthorisedActionDao authorisedActionDao;
  private final ObjectMapper objectMapper;

  @Autowired
  public AuthorisedActionServiceImpl(
      AuthorisedActionDao authorisedActionDao, ObjectMapper objectMapper) {
    this.authorisedActionDao = authorisedActionDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public AuthorisedActionDto getAuthorisedActionByAuthority(String authority) {
    AuthorisedAction authorisedAction = authorisedActionDao.findByAuthority(authority).orElse(null);
    if (authorisedAction == null) return null;
    return authorisedAction.toDto();
  }

  @Override
  public AuthorisedActionDto getAuthorisedActionById(Long id) {
    AuthorisedAction authorisedAction = authorisedActionDao.findById(id).orElse(null);
    if (authorisedAction == null) return null;
    return authorisedAction.toDto();
  }

  @Override
  public AuthorisedActionDto createAuthorisedAction(AuthorisedAction authorisedAction) {
    return authorisedActionDao.saveAuthorisedAction(authorisedAction).toDto();
  }

  @Override
  public AuthorisedActionDto updateAuthorisedAction(Long id, AuthorisedActionDto updateDto) {
    AuthorisedAction authorisedAction =
        authorisedActionDao
            .findById(id)
            .orElseThrow(
                () -> new RuntimeException("authorisedAction with id [" + id + "] does not exist"));
    try {
      objectMapper.updateValue(authorisedAction, updateDto);
      AuthorisedAction updatedAuthorisedAction =
          authorisedActionDao.saveAuthorisedAction(authorisedAction);
      return updatedAuthorisedAction.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteAuthorisedActionById(Long id) {
    authorisedActionDao.deleteAuthorisedActionById(id);
  }

  @Override
  public List<AuthorisedActionDto> getAllAuthorisedActions() {
    List<AuthorisedAction> authorisedActions = authorisedActionDao.findAllAuthorisedActions();
    return authorisedActions.stream().map(AuthorisedAction::toDto).collect(Collectors.toList());
  }
}
