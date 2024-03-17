package com.nusiss.inventory.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nusiss.inventory.backend.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Long id;
    private String customerName;
    private String customerContact;

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCustomerName(customerName);
        customer.setCustomerContact(customerContact);
        return customer;
    }
}
