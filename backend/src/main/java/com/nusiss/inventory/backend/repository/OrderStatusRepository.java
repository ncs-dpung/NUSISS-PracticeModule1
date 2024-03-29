package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
