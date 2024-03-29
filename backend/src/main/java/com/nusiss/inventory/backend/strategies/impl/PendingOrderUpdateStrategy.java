package com.nusiss.inventory.backend.strategies.impl;

import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.dto.OrderItemDto;
import com.nusiss.inventory.backend.entity.Customer;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.entity.OrderItem;
import com.nusiss.inventory.backend.entity.User;
import com.nusiss.inventory.backend.repository.CustomerRepository;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.UserRepository;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Component("PendingOrderUpdateStrategy")
public class PendingOrderUpdateStrategy implements OrderUpdateStrategy {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final OrderConverter orderConverter;

    @Autowired
    public PendingOrderUpdateStrategy(OrderRepository orderRepository, UserRepository userRepository, CustomerRepository customerRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.orderConverter = orderConverter;
    }


    @Override
    public OrderDto updateOrder(Order order, OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + orderDto.getUserId()));
        order.setUser(user);

        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + orderDto.getCustomerId()));
        order.setCustomer(customer);

        // Update other fields
        order.setDatePlaced(orderDto.getDatePlaced());
        order.setStatus(orderDto.getStatus());
        if(order.getStatus().getName().equals("Delivered")) order.setDateShipped(LocalDateTime.now());
        else order.setDateShipped(null);

        // Update OrderItems
        // First, create a map of current items for easy lookup
        Map<Long, OrderItem> currentItemMap = order.getItems().stream()
                .collect(Collectors.toMap(item -> item.getProduct().getId(), item -> item, (item1, item2) -> item1));

        for (OrderItemDto itemDto : orderDto.getItems()) {
            OrderItem existingItem = currentItemMap.remove(itemDto.getProductId());
            if (existingItem != null) {
                // Item exists, update its quantity and price
                existingItem.setQuantity(itemDto.getQuantity());
                existingItem.setPrice(itemDto.getPrice());
            } else {
                // New item, add it to the order
                OrderItem newItem = orderConverter.convertOrderItemDtoToEntity(itemDto, order);
                order.getItems().add(newItem); // Associate with the current order
            }
        }

        // Remove items that were not included in the updated DTO
        currentItemMap.values().forEach(order.getItems()::remove);

        orderRepository.save(order);
        return orderConverter.convertOrderToDto(order);
    }
}
