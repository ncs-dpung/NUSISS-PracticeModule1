package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.components.ProductComponent;
import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.entity.Category;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.entity.Supplier;
import com.nusiss.inventory.backend.repository.CategoryRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.repository.SupplierRepository;
import com.nusiss.inventory.backend.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;
  private final SupplierRepository supplierRepository;
  private final ProductComponent productConverter;

  @Autowired
  public ProductServiceImpl(
      ProductRepository productRepository,
      CategoryRepository categoryRepository,
      SupplierRepository supplierRepository,
      ProductComponent productConverter) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.supplierRepository = supplierRepository;
    this.productConverter = productConverter;
  }

  @Override
  @Transactional
  public Product createProduct(ProductDto productDTO) {
    Category category =
        categoryRepository
            .findById(productDTO.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    Supplier supplier =
        supplierRepository
            .findById(productDTO.getSupplierId())
            .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

    Product product = new Product();
    // Set fields from DTO, including fetched category and supplier
    updateProductFields(product, productDTO, category, supplier);
    return productRepository.save(product);
  }

  @Override
  @Transactional
  public Product updateProduct(Long productId, ProductDto productDTO) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    Category category =
        categoryRepository
            .findById(productDTO.getCategoryId())
            .orElseThrow(() -> new EntityNotFoundException("Category not found"));
    Supplier supplier =
        supplierRepository
            .findById(productDTO.getSupplierId())
            .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

    updateProductFields(product, productDTO, category, supplier);
    return productRepository.save(product);
  }

  @Override
  @Transactional
  public void deleteProduct(Long productId) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    if (product.getQuantityAvailable() != 0) {
      throw new IllegalStateException("Cannot delete product with quantity more than 0.");
    }

    productRepository.deleteById(productId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductDto> findAllProducts() {
    return productRepository.findAllWithDetails().stream()
        .map(productConverter::convertToDTO)
        .collect(Collectors.toList());
  }

  // Helper methods
  private void updateProductFields(
      Product product, ProductDto productDTO, Category category, Supplier supplier) {
    product.setName(productDTO.getName());
    product.setCategory(
        category); // category is already fetched based on productDTO.getCategoryId()
    product.setPrice(productDTO.getPrice());
    product.setBatchNo(productDTO.getBatchNo());
    product.setSupplier(
        supplier); // supplier is already fetched based on productDTO.getSupplierId()
    product.setQuantityAvailable(productDTO.getQuantityAvailable());
    product.setReorderLevel(productDTO.getReorderLevel());
  }
}
