package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.ProductDto;
import java.util.List;

public interface InventoryService {
  // void checkAndNotifyForReorder();
  List<ProductDto> findProductsNeedingReorder();

  void reorderProduct(Long productId, int quantity);
}
