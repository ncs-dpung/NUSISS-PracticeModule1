package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.SupplierDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "tbl_supplier")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "supplier_id")
  private Long id;

  @Column(name = "supplier_name")
  private String supplierName;

  @Column(name = "contact_info")
  private String supplierContact;

  @Column(name = "address")
  private String supplierAddress;

  public SupplierDto toDto() {
    SupplierDto dto = new SupplierDto();
    dto.setId(id);
    dto.setSupplierName(supplierName);
    dto.setSupplierContact(supplierContact);
    dto.setSupplierAddress(supplierAddress);
    return dto;
  }
}
