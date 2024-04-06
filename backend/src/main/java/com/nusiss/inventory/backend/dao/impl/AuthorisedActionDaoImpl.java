package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.AuthorisedActionDao;
import com.nusiss.inventory.backend.entity.AuthorisedAction;
import com.nusiss.inventory.backend.repository.AuthorisedActionRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorisedActionDaoImpl implements AuthorisedActionDao {
  private final AuthorisedActionRepository authorisedActionRepository;

  @Autowired
  public AuthorisedActionDaoImpl(AuthorisedActionRepository authorisedActionRepository) {
    this.authorisedActionRepository = authorisedActionRepository;
  }

  @Override
  public Optional<AuthorisedAction> findById(Long id) {
    return authorisedActionRepository.findById(id);
  }

  @Override
  public Optional<AuthorisedAction> findByAuthority(String authority) {
    return authorisedActionRepository.findByAuthority(authority);
  }

  @Transactional
  @Override
  public AuthorisedAction saveAuthorisedAction(AuthorisedAction authorisedAction) {
    return authorisedActionRepository.save(authorisedAction);
  }

  @Override
  public void deleteAuthorisedActionById(Long id) {
    authorisedActionRepository.deleteById(id);
    return;
  }

  @Override
  public List<AuthorisedAction> findAllAuthorisedActions() {
    return authorisedActionRepository.findAll();
  }
}
