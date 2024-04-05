package com.nusiss.inventory.backend.observers.impl;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.observers.InventoryObserver;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification implements InventoryObserver {

  @Override
  public void update(ProductDto product) {
    // Logic to send email notification
    System.out.println("Sending email notification for low stock: " + product.getName());
  }
}
