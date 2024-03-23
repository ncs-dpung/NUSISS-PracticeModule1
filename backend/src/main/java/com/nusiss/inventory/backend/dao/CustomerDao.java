package com.nusiss.inventory.backend.dao;

import com.nusiss.inventory.backend.entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
  Optional<Customer> findById(Long id);

  Customer saveCustomer(Customer customer);

  void deleteCustomerById(Long id);

  List<Customer> findAllCustomer();
}
