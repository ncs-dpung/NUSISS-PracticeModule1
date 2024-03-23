package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.dao.CustomerDao;
import com.nusiss.inventory.backend.dto.CustomerDto;
import com.nusiss.inventory.backend.entity.Customer;
import com.nusiss.inventory.backend.repository.CustomerRepository;
import com.nusiss.inventory.backend.service.CustomerService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerDao customerDao;

  private final ObjectMapper objectMapper;

  public CustomerServiceImpl(
      CustomerRepository customerRepository, CustomerDao customerDao, ObjectMapper objectMapper) {
    this.customerRepository = customerRepository;
    this.customerDao = customerDao;
    this.objectMapper = objectMapper;
  }

  @Override
  public CustomerDto getCustomerById(Long id) {
    return customerRepository.findById(id).orElse(null).toDto();
  }

  @Override
  public CustomerDto createCustomer(CustomerDto customerDto) {
    return customerDao.saveCustomer(customerDto.toEntity()).toDto();
  }

  @Override
  public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(
                () -> new RuntimeException("Customer with id [" + id + "] does not exist"));

    try {
      objectMapper.updateValue(customer, customerDto);
      Customer updatedCustomer = customerDao.saveCustomer(customer);
      return updatedCustomer.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void deleteCustomerById(Long id) {
    customerDao.deleteCustomerById(id);
  }

  @Override
  public List<CustomerDto> getAllCustomer() {
    List<Customer> customers = customerDao.findAllCustomer();
    return customers.stream().map(Customer::toDto).toList();
  }
}
