package com.nusiss.inventory.backend.strategies.impl;

import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;
import org.springframework.stereotype.Component;

@Component("DELIVEREDOrderUpdateStrategy")
public class DeliveredOrderUpdateStrategy implements OrderUpdateStrategy {
  @Override
  public OrderDto updateOrder(Order order, OrderDto orderDto) {
    throw new IllegalStateException("Order updates are restricted for DELIVERED orders.");
  }
}
