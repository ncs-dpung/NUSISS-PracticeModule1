package com.nusiss.inventory.backend.strategies.impl;

import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PROCESSEDOrderUpdateStrategy")
public class ProcessedOrderUpdateStrategy implements OrderUpdateStrategy {

  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;

  @Autowired
  public ProcessedOrderUpdateStrategy(
      OrderRepository orderRepository, OrderConverter orderConverter) {
    this.orderRepository = orderRepository;
    this.orderConverter = orderConverter;
  }

  @Override
  public OrderDto updateOrder(Order order, OrderDto orderDto) {

    // Update other fields
    order.setStatus(orderDto.getStatus());
    if (order.getStatus().getName().equals("Delivered")) order.setDateShipped(new Date());
    else order.setDateShipped(null);

    orderRepository.save(order);
    return orderConverter.convertOrderToDto(order);
  }
}
