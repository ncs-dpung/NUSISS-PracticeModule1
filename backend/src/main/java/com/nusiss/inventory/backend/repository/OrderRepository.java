package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

