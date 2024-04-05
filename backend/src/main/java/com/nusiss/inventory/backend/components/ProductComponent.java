package com.nusiss.inventory.backend.components;

import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductComponent {
    private final ProductRepository productRepository;

    public ProductComponent(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findProductsNeedingReorder() {
        return productRepository.findProductsNeedingReorder().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProductDto convertToDTO(Product product) {
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
