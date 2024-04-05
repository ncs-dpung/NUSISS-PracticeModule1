package com.nusiss.inventory.backend.observers.impl;

import com.nusiss.inventory.backend.components.ProductComponent;
import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.observers.InventoryObserver;
import com.nusiss.inventory.backend.observers.InventorySubject;
import com.nusiss.inventory.backend.repository.ProductRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryManager implements InventorySubject {

  private final List<InventoryObserver> observers;
  private final ProductRepository productRepository;
  private final ProductComponent productComponent;


  public InventoryManager(
      ProductRepository productRepository,
      ProductComponent productComponent) {
    this.productRepository = productRepository;
    this.observers = new ArrayList<>();
    this.productComponent = productComponent;
  }

  @Override
  public void addObserver(InventoryObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(InventoryObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    List<ProductDto> productsNeedingReorder = productComponent.findProductsNeedingReorder();
    productsNeedingReorder.forEach(
        productDto -> observers.forEach(observer -> observer.update(productDto)));
  }

  @Scheduled(fixedRate = 60000) // Executes every 1 minutes
  public void checkAndNotifyForReorder() {
    notifyObservers();
  }
}
