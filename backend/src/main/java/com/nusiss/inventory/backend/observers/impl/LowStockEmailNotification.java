package com.nusiss.inventory.backend.observers.impl;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.observers.InventoryObserver;
import com.nusiss.inventory.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LowStockEmailNotification implements InventoryObserver {

  private final NotificationService notificationService;
  @Autowired
  public LowStockEmailNotification(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @Override
  public void update(ProductDto product) {
    System.out.println("Low stock notification sent for product " + product.getName());
    notificationService.sendNotification("admin@admin.com", "Low Stock Notification", "Product " + product.getName() + " is running low on stock.");
  }
}
