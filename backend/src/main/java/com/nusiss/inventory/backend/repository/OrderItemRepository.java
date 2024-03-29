package com.nusiss.inventory.backend.repository;

import com.nusiss.inventory.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

