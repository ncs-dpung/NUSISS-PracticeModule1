package com.nusiss.inventory.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository<Role> extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

