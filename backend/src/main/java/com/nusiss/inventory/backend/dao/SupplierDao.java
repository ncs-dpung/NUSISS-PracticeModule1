package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierDao {
  Optional<Supplier> findById(Long id);

  Supplier saveSupplier(Supplier supplier);

  void deleteSupplierById(Long id);

  List<Supplier> findAllSupplier();
}
