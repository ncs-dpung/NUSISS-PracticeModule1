package com.nusiss.inventory.backend.observers.impl;

import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.observers.OrderStatusObserver;
import com.nusiss.inventory.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeliveryStatusObserver implements OrderStatusObserver {

  private final NotificationService notificationService;

  @Autowired
  public CustomerDeliveryStatusObserver(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @Override
  public void notify(Order order) {
    if ("Delivered".equals(order.getStatus().getName())) {
      String subject = "Order Delivered";
      String message = "Your order " + order.getOrderId() + " has been delivered successfully.";
      notificationService.sendNotification(
          order.getCustomer().getCustomerContact(), subject, message);
    }
  }
}
