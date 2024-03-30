package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.json.StatusUpdateRequest;

import java.util.List;

public interface OrderService {
    OrderDto findById(Long orderId);
    OrderDto createOrder(OrderDto OrderDto);
    OrderDto updateOrderStatus(Long orderId, StatusUpdateRequest statusUpdateRequest);
    List<OrderDto> findAllOrders();
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    void deleteOrder(Long orderId);
    List<OrderDto> findPendingAndProcessedOrdersSorted();
}
