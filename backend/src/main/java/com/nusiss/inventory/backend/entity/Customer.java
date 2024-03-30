package com.nusiss.inventory.backend.entity;

import com.nusiss.inventory.backend.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "tbl_customer")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_id")
  private Long id;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "contact_info")
  private String customerContact;

  // private String customerAddress;

  public CustomerDto toDto() {
    CustomerDto dto = new CustomerDto();
    dto.setId(id);
    dto.setCustomerName(customerName);
    dto.setCustomerContact(customerContact);
    // dto.setCustomerAddress(customerAddress);
    return dto;
  }
}
