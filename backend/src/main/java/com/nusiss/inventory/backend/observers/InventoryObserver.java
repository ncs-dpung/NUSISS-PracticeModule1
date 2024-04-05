package com.nusiss.inventory.backend.observers;

import com.nusiss.inventory.backend.dto.ProductDto;

public interface InventoryObserver {
  void update(ProductDto product);
}
