package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.SupplierDao;
import com.nusiss.inventory.backend.dto.ProductDto;
import com.nusiss.inventory.backend.dto.SupplierDto;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.entity.Supplier;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.repository.SupplierRepository;
import com.nusiss.inventory.backend.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

  private final SupplierRepository supplierRepository;
  private final ProductRepository productRepository;
  private final SupplierDao supplierDao;
  private final ObjectMapper objectMapper;

  @Autowired
  public SupplierServiceImpl(
      SupplierRepository supplierRepository,
      ProductRepository productRepository,
      SupplierDao supplierDao,
      ObjectMapper objectMapper) {
    this.supplierRepository = supplierRepository;
    this.productRepository = productRepository;
    this.supplierDao = supplierDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public SupplierDto getSupplierById(Long id) {
    return supplierRepository.findById(id).map(Supplier::toDto).orElse(null);
  }


  @Override
  @Transactional
  public SupplierDto createSupplier(SupplierDto supplierDto) {
    return supplierDao.saveSupplier(supplierDto.toEntity()).toDto();
  }

  @Override
  @Transactional
  public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
    Supplier supplier =
        supplierRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Supplier with id [" + id + "] does not exist"));

    try {
      objectMapper.updateValue(supplier, supplierDto);
      Supplier updatedSupplier = supplierDao.saveSupplier(supplier);
      return updatedSupplier.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  @Transactional
  public void deleteSupplierById(Long id) {
    // Check if any products are associated with this supplier
    long productCount = productRepository.countBySupplierId(id);
    if (productCount > 0) {
      throw new IllegalStateException("Cannot delete supplier as there are associated products.");
    }
    // If no products are associated, proceed with deletion
    supplierRepository.deleteById(id);
  }

  @Override
  public List<SupplierDto> getAllSupplier() {
    List<Supplier> suppliers = supplierRepository.findAll();
    return suppliers.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  public SupplierDto convertToDto(Supplier supplier) {
    if (supplier == null) {
      throw new IllegalArgumentException("Supplier cannot be null");
    }

    SupplierDto dto = new SupplierDto();
    dto.setId(supplier.getId());
    dto.setSupplierName(supplier.getSupplierName());
    dto.setSupplierContact(supplier.getSupplierContact());
    dto.setSupplierAddress(supplier.getSupplierAddress());

    // Fetch and add associated products to the DTO
    List<ProductDto> productDtos =
        productRepository.findBySupplierId(supplier.getId()).stream()
            .map(this::convertProductToDto)
            .collect(Collectors.toList());
    dto.setProducts(productDtos);

    return dto;
  }

  private ProductDto convertProductToDto(Product product) {
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
