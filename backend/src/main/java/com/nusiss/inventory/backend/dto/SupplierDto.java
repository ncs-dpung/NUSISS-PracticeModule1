package com.nusiss.inventory.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nusiss.inventory.backend.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDto {
  private Long id;
  private String supplierName;
  private String supplierContact;
  private String supplierAddress;
  private List<ProductDto> products;

  public Supplier toEntity() {
    Supplier supplier = new Supplier();
    supplier.setId(id);
    supplier.setSupplierName(supplierName);
    supplier.setSupplierContact(supplierContact);
    supplier.setSupplierAddress(supplierAddress);
    return supplier;
  }
}
