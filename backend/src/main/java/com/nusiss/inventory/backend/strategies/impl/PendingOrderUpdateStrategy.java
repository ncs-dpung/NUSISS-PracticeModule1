package com.nusiss.inventory.backend.strategies.impl;

import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.dto.OrderItemDto;
import com.nusiss.inventory.backend.entity.*;
import com.nusiss.inventory.backend.repository.CustomerRepository;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.repository.UserRepository;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component("PENDINGOrderUpdateStrategy")
public class PendingOrderUpdateStrategy implements OrderUpdateStrategy {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private final OrderConverter orderConverter;

    @Autowired
    public PendingOrderUpdateStrategy(OrderRepository orderRepository, UserRepository userRepository, CustomerRepository customerRepository, ProductRepository productRepository, OrderConverter orderConverter) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderConverter = orderConverter;
    }


    @Override
    @Transactional
    public OrderDto updateOrder(Order order, OrderDto orderDto) {
        // TODO: Uncomment the following line when user management is implemented
//        User user = userRepository.findById(orderDto.getUserId())
//                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + orderDto.getUserId()));
//        order.setUser(user);

        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + orderDto.getCustomerId()));
        order.setCustomer(customer);

        // Update other fields
        order.setDatePlaced(orderDto.getDatePlaced());
        order.setStatus(orderDto.getStatus());
        if(order.getStatus().getName().equals("Delivered")) order.setDateShipped(new Date());
        else order.setDateShipped(null);

        // Update OrderItems
        updateOrderItems(order, orderDto.getItems());

        orderRepository.save(order);
        return orderConverter.convertOrderToDto(order);
    }

    private void updateOrderItems(Order order, Collection<OrderItemDto> itemDtos) {
        // Temporary map to track updates
        Map<Long, OrderItem> currentItemMap = new HashMap<>();
        order.getItems().forEach(item -> currentItemMap.put(item.getProduct().getId(), item));

        // Update or add items
        for (OrderItemDto dto : itemDtos) {
            Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());

            if (!optionalProduct.isPresent()) {
                // If the product doesn't exist anymore, skip this iteration
                continue;
            }

            Product product = optionalProduct.get();
            OrderItem item = currentItemMap.remove(dto.getProductId());

            if (item != null) {
                // Existing item, update quantity
                int quantityDiff = dto.getQuantity() - item.getQuantity();
                product.adjustInventory(-quantityDiff); // adjustInventory handles negative checks
                item.setQuantity(dto.getQuantity());
                item.setPrice(dto.getPrice());
            } else {
                // New item, adjust inventory and add to order
                product.adjustInventory(-dto.getQuantity());
                OrderItem newItem = orderConverter.convertOrderItemDtoToEntity(dto, order);
                order.getItems().add(newItem);
            }

            // Only save the product if it exists
            productRepository.save(product);
        }

        // Remove items not in the updated DTO and adjust inventory accordingly
        currentItemMap.values().forEach(removedItem -> {
            Optional<Product> optionalProduct = productRepository.findById(removedItem.getProduct().getId());

            if (!optionalProduct.isPresent()) {
                // If the product doesn't exist anymore, just remove the item from the order
                order.getItems().remove(removedItem);
                return; // Skip further processing for this removed item
            }

            Product product = optionalProduct.get();
            product.adjustInventory(removedItem.getQuantity()); // Add back the removed quantity to inventory
            order.getItems().remove(removedItem);
            productRepository.save(product);
        });
    }

}
