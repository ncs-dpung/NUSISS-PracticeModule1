package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.SupplierDao;
import com.nusiss.inventory.backend.entity.Supplier;
import com.nusiss.inventory.backend.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierDaoImpl implements SupplierDao {

    private final SupplierRepository supplierRepository;

    public SupplierDaoImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Transactional
    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Transactional
    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findAllSupplier() {
        return supplierRepository.findAll();
    }
}
