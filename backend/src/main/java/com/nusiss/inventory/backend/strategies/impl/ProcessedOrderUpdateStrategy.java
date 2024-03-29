package com.nusiss.inventory.backend.strategies.impl;

import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.dto.OrderItemDto;
import com.nusiss.inventory.backend.entity.Customer;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.entity.OrderItem;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Component("PROCESSEDOrderUpdateStrategy")
public class ProcessedOrderUpdateStrategy implements OrderUpdateStrategy {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    @Autowired
    public ProcessedOrderUpdateStrategy(OrderRepository orderRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.orderConverter = orderConverter;
    }



    @Override
    public OrderDto updateOrder(Order order, OrderDto orderDto) {

        // Update other fields
        order.setStatus(orderDto.getStatus());
        if(order.getStatus().getName().equals("DELIVERED")) order.setDateShipped(LocalDateTime.now());
        else order.setDateShipped(null);

        orderRepository.save(order);
        return orderConverter.convertOrderToDto(order);
    }
}
