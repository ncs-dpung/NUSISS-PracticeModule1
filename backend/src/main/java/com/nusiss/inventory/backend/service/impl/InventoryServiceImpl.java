package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.InventoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    @Autowired
    public InventoryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    /**
    @Override
    @Scheduled(fixedRate = 120000 ) // Every 2 minutes
    public void checkAndNotifyForReorder() {
        findProductsNeedingReorder().forEach(product -> {
            // Log, notify, or initiate reorder for each product needing it
            System.out.println("Reorder needed for product: " + product.getName());
        });
    }**/

    @Override
    public List<ProductDto> findProductsNeedingReorder() {
        return productRepository.findProductsNeedingReorder().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void reorderProduct(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setQuantityAvailable(product.getQuantityAvailable() + quantity);
        productRepository.save(product);
    }

    private ProductDto convertToDTO(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategoryId(product.getCategory().getCategoryId());
        dto.setCategoryName(product.getCategory().getCategoryName());
        dto.setSupplierId(product.getSupplier().getId());
        dto.setSupplierName(product.getSupplier().getSupplierName());
        dto.setPrice(product.getPrice());
        dto.setBatchNo(product.getBatchNo());
        dto.setQuantityAvailable(product.getQuantityAvailable());
        dto.setReorderLevel(product.getReorderLevel());
        dto.setStockLevel(product.getStockLevel());
        return dto;
    }
}

