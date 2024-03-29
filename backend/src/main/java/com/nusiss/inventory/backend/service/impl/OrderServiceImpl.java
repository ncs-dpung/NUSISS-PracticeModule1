package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.entity.*;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.OrderStatusRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.response.StatusUpdateRequest;
import com.nusiss.inventory.backend.service.OrderService;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final Map<String, OrderUpdateStrategy> updateStrategies;
    private final OrderConverter orderConverter;

    @Autowired
    public OrderServiceImpl(OrderConverter orderConverter, OrderRepository orderRepository, OrderStatusRepository orderStatusRepository, ProductRepository productRepository, Map<String, OrderUpdateStrategy> updateStrategies) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.productRepository = productRepository;
        this.orderConverter = orderConverter;
        this.updateStrategies = updateStrategies;
    }

    @Override
    public OrderDto findById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        return orderConverter.convertOrderToDto(order);
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        // Assuming you have a mapping method or using a mapping library like MapStruct
        order = orderConverter.convertOrderDtoToEntity(orderDto);

        // Deduct inventory for each order item. This is a simplified placeholder logic.
        order.getItems().forEach(item -> {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + item.getProduct().getId()));
            if (product.getQuantityAvailable() < item.getQuantity()) {
                throw new RuntimeException("Not enough inventory for product ID: " + item.getProduct().getId());
            }
            product.setQuantityAvailable(product.getQuantityAvailable() - item.getQuantity());
            productRepository.save(product);
        });

        order = orderRepository.save(order);
        return orderConverter.convertOrderToDto(order);
    }

    @Override
    @Transactional
    public OrderDto updateOrderStatus(Long orderId, StatusUpdateRequest statusUpdateRequest) {
        Long statusId = statusUpdateRequest.getStatus().getId();
        LocalDateTime deliveryDate = statusUpdateRequest.getDeliveryDate();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));

        OrderStatus status = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException("OrderStatus not found with ID: " + statusId));
        order.setStatus(status);
        // Check if the status is 'Delivered'
        if ("Delivered".equals(status.getName())) {
            order.setDateShipped(deliveryDate != null ? deliveryDate : LocalDateTime.now());
        } else {
            order.setDateShipped(null); // clear the dateShipped if the status is not Delivered
        }
        orderRepository.save(order);
        return orderConverter.convertOrderToDto(order);
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderConverter::convertOrderToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));

        String statusName = order.getStatus().getName().toUpperCase();

        OrderUpdateStrategy strategy = updateStrategies.get(statusName + "OrderUpdateStrategy");

        if (strategy == null) {
            throw new IllegalStateException("No update strategy found for status: " + statusName);
        }

        return strategy.updateOrder(order, orderDto);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderId));
        if (!"Pending".equals(order.getStatus().getName())) {
            throw new IllegalStateException("Order can only be deleted if it is in Pending status.");
        }
        orderRepository.delete(order);
    }
}

