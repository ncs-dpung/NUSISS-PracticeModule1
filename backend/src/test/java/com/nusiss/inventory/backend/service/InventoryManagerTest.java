package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.components.ProductComponent;
import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.observers.InventoryObserver;
import com.nusiss.inventory.backend.observers.impl.InventoryManager;
import com.nusiss.inventory.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class InventoryManagerTest {

    @Mock
    private ProductComponent productComponent;

    @Mock
    private InventoryObserver inventoryObserver;

    @InjectMocks
    private InventoryManager inventoryManager;

    @Test
    void checkAndNotifyForReorder() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        when(productComponent.findProductsNeedingReorder()).thenReturn(Collections.singletonList(productDto));

        inventoryManager.checkAndNotifyForReorder();

        verify(productComponent).findProductsNeedingReorder();
    }
}

