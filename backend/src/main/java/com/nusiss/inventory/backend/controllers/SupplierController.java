package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.SupplierDto;
import com.nusiss.inventory.backend.service.SupplierService;
import io.swagger.annotations.*;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
@Api(value = "Supplier Management", tags = "Supplier Controller")
public class SupplierController {

  private final SupplierService supplierService;

  public SupplierController(SupplierService supplierService) {
    this.supplierService = supplierService;
  }

  @ApiOperation(value = "Get a list of all suppliers")
  @GetMapping
  public ResponseEntity<List<SupplierDto>> getAllSupplier() {
    List<SupplierDto> suppliers = supplierService.getAllSupplier();
    return ResponseEntity.ok(suppliers);
  }

  @ApiOperation(value = "Get a supplier by ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Successfully retrieved supplier"),
        @ApiResponse(code = 404, message = "Supplier not found")
      })
  @GetMapping("/{id}")
  public ResponseEntity<SupplierDto> getSupplierById(
      @ApiParam(value = "Supplier ID", required = true) @PathVariable Long id) {
    SupplierDto supplierDto = supplierService.getSupplierById(id);
    return ResponseEntity.ok(supplierDto);
  }

  @ApiOperation(value = "Create a new supplier")
  @PostMapping
  @Transactional
  public ResponseEntity<SupplierDto> createSupplier(
      @ApiParam(value = "Supplier details", required = true) @RequestBody SupplierDto supplierDto) {
    SupplierDto newSupplier = supplierService.createSupplier(supplierDto);
    return ResponseEntity.ok(newSupplier);
  }

  @ApiOperation(value = "Update a supplier")
  @Transactional
  @PutMapping("/{id}")
  public ResponseEntity<SupplierDto> updateSupplier(
      @ApiParam(value = "Supplier ID", required = true) @PathVariable Long id,
      @ApiParam(value = "Supplier details", required = true) @RequestBody SupplierDto supplierDto) {
    SupplierDto updatedSupplier = supplierService.updateSupplier(id, supplierDto);
    return ResponseEntity.ok(updatedSupplier);
  }

  @ApiOperation(value = "Delete a supplier")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSupplier(
      @ApiParam(value = "Supplier ID", required = true) @PathVariable Long id) {
    supplierService.deleteSupplierById(id);
    return ResponseEntity.noContent().build();
  }
}
