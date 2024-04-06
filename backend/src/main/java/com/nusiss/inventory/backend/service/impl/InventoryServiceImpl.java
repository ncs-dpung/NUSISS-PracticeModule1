package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.components.ProductComponent;
import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

  private final ProductRepository productRepository;
  private final ProductComponent productComponent;

  @Autowired
  public InventoryServiceImpl(
      ProductRepository productRepository, ProductComponent productComponent) {
    this.productRepository = productRepository;
    this.productComponent = productComponent;
  }

  @Override
  public List<ProductDto> findProductsNeedingReorder() {
    return productComponent.findProductsNeedingReorder();
  }

  @Override
  @Transactional
  public void reorderProduct(Long productId, int quantity) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
    productRepository.save(product);
  }
}
