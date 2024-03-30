package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.AuthorisedAction;
import java.util.List;
import java.util.Optional;

public interface AuthorisedActionDao {
  Optional<AuthorisedAction> findById(Long id);

  Optional<AuthorisedAction> findByAuthority(String authority);

  AuthorisedAction saveAuthorisedAction(AuthorisedAction role);

  void deleteAuthorisedActionById(Long id);

  List<AuthorisedAction> findAllAuthorisedActions();
}
