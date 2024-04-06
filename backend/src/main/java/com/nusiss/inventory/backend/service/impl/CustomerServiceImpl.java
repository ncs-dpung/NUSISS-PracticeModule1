package com.nusiss.inventory.backend.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dao.CustomerDao;
import com.nusiss.inventory.backend.dto.CustomerDto;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.entity.Customer;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.repository.CustomerRepository;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerDao customerDao;
  private final ObjectMapper objectMapper;
  private final OrderConverter orderConverter;
  private final OrderRepository orderRepository;

  @Autowired
  public CustomerServiceImpl(
      CustomerRepository customerRepository,
      CustomerDao customerDao,
      ObjectMapper objectMapper,
      OrderConverter orderConverter,
      OrderRepository orderRepository) {
    this.customerRepository = customerRepository;
    this.customerDao = customerDao;
    this.objectMapper = objectMapper;
    this.orderRepository = orderRepository;
    this.orderConverter = orderConverter;
  }

  @Override
  public CustomerDto getCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

    CustomerDto dto = new CustomerDto();
    dto.setId(customer.getId());
    dto.setCustomerName(customer.getCustomerName());
    dto.setCustomerContact(customer.getCustomerContact());

    // Fetch and convert associated orders
    List<Order> orders = orderRepository.findByCustomerId(customer.getId());
    List<OrderDto> orderDtos =
        orders.stream().map(orderConverter::convertOrderToDto).collect(Collectors.toList());
    dto.setOrders(orderDtos);

    return dto;
  }

  @Override
  @Transactional
  public CustomerDto createCustomer(CustomerDto customerDto) {
    return customerDao.saveCustomer(customerDto.toEntity()).toDto();
  }

  @Override
  @Transactional
  public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Customer with id [" + id + "] does not exist"));

    try {
      objectMapper.updateValue(customer, customerDto);
      Customer updatedCustomer = customerDao.saveCustomer(customer);
      return updatedCustomer.toDto();
    } catch (JsonMappingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  @Transactional
  public void deleteCustomerById(Long id) {
    customerDao.deleteCustomerById(id);
  }

  public List<CustomerDto> getAllCustomerWithOrders() {
    List<Customer> customers = customerRepository.findAll();
    return customers.stream()
        .map(
            customer -> {
              CustomerDto dto = new CustomerDto();
              dto.setId(customer.getId());
              dto.setCustomerName(customer.getCustomerName());
              dto.setCustomerContact(customer.getCustomerContact());

              // Fetch and convert associated orders
              List<Order> orders = orderRepository.findByCustomerId(customer.getId());
              List<OrderDto> orderDtos =
                  orders.stream()
                      .map(orderConverter::convertOrderToDto)
                      .collect(Collectors.toList());
              dto.setOrders(orderDtos);

              return dto;
            })
        .collect(Collectors.toList());
  }
}
