package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  @Query("SELECT u FROM User u WHERE u.email = ?1")
  Optional<User> findByEmailAddress(String emailAddress);
}
