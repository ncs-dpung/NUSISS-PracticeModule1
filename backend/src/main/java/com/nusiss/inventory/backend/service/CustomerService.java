package com.nusiss.inventory.backend.service;

import com.nusiss.inventory.backend.dto.CustomerDto;
import java.util.List;

public interface CustomerService {
  CustomerDto getCustomerById(Long id);

  CustomerDto createCustomer(CustomerDto customerDto);

  CustomerDto updateCustomer(Long id, CustomerDto customerDto);

  void deleteCustomerById(Long id);

  List<CustomerDto> getAllCustomerWithOrders();
}
