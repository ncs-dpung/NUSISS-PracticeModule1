package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.SupplierDto;
import java.util.List;

public interface SupplierService {
  SupplierDto getSupplierById(Long id);

  SupplierDto createSupplier(SupplierDto supplierDto);

  SupplierDto updateSupplier(Long id, SupplierDto supplierDto);

  void deleteSupplierById(Long id);

  List<SupplierDto> getAllSupplier();
}
