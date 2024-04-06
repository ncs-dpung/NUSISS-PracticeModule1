package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.AuthorisedAction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorisedActionRepository extends JpaRepository<AuthorisedAction, Long> {
  Optional<AuthorisedAction> findByAuthority(String authority);
}
