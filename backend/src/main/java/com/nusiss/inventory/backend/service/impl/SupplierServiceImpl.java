package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.SupplierDao;
import com.nusiss.inventory.backend.dto.SupplierDto;
import com.nusiss.inventory.backend.entity.Supplier;
import com.nusiss.inventory.backend.repository.SupplierRepository;
import com.nusiss.inventory.backend.service.SupplierService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

  private final SupplierRepository supplierRepository;
  private final SupplierDao supplierDao;
  private final ObjectMapper objectMapper;

  public SupplierServiceImpl(
      SupplierRepository supplierRepository, SupplierDao supplierDao, ObjectMapper objectMapper) {
    this.supplierRepository = supplierRepository;
    this.supplierDao = supplierDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public SupplierDto getSupplierById(Long id) {
    return supplierRepository.findById(id).orElse(null).toDto();
  }

  @Override
  public SupplierDto createSupplier(SupplierDto supplierDto) {
    return supplierDao.saveSupplier(supplierDto.toEntity()).toDto();
  }

  @Override
  public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
    Supplier supplier =
        supplierRepository
            .findById(id)
            .orElseThrow(
                () -> new RuntimeException("Supplier with id [" + id + "] does not exist"));

    try {
      objectMapper.updateValue(supplier, supplierDto);
      Supplier updatedSupplier = supplierDao.saveSupplier(supplier);
      return updatedSupplier.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteSupplierById(Long id) {
    supplierDao.deleteSupplierById(id);
  }

  @Override
  public List<SupplierDto> getAllSupplier() {
    List<Supplier> suppliers = supplierDao.findAllSupplier();
    return suppliers.stream().map(Supplier::toDto).collect(Collectors.toList());
  }
}
