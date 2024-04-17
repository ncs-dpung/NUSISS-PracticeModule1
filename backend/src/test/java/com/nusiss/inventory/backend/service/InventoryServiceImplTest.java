package com.nusiss.inventory.backend.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nusiss.inventory.backend.components.ProductComponent;
import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.impl.InventoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceImplTest {

  @Mock private ProductRepository productRepository;
  @Mock private ProductComponent productComponent;

  private InventoryServiceImpl inventoryService;

  @BeforeEach
  public void setUp() {
    inventoryService = new InventoryServiceImpl(productRepository, productComponent);
  }

  @Test
  void testFindProductsNeedingReorder() {
    List<ProductDto> expectedProducts = List.of(new ProductDto());
    when(productComponent.findProductsNeedingReorder()).thenReturn(expectedProducts);

    List<ProductDto> result = inventoryService.findProductsNeedingReorder();

    assertNotNull(result);
    assertEquals(expectedProducts, result);
    verify(productComponent).findProductsNeedingReorder();
  }

  //    @Test
  //    void testReorderProduct_Success() {
  //        Long productId = 1L;
  //        int quantityToAdd = 5;
  //        Product product = new Product();
  //        product.setQuantityAvailable(10);
  //
  //        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
  //        doNothing().when(productRepository).save(product);
  //
  //        assertDoesNotThrow(() -> inventoryService.reorderProduct(productId, quantityToAdd));
  //        assertEquals(15, product.getQuantityAvailable());
  //        verify(productRepository).save(product);
  //    }

  @Test
  void testReorderProduct_ProductNotFound() {
    Long productId = 1L;
    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    assertThrows(
        EntityNotFoundException.class, () -> inventoryService.reorderProduct(productId, 5));
  }
}
