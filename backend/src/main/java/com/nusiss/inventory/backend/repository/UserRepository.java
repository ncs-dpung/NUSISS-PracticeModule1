package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmailAddress(String emailAddress);
}
