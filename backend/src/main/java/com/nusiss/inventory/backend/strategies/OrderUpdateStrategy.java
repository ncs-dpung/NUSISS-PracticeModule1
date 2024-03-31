package com.nusiss.inventory.backend.strategies;

import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.entity.Order;

public interface OrderUpdateStrategy {
  OrderDto updateOrder(Order order, OrderDto orderDto);
}
