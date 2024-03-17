package com.nusiss.inventory.backend.dao.impl;

import com.nusiss.inventory.backend.dao.CustomerDao;
import com.nusiss.inventory.backend.entity.Customer;
import com.nusiss.inventory.backend.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDaoImpl implements CustomerDao {

    private final CustomerRepository customerRepository;

    public CustomerDaoImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        // TODO: review when implementing Order
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }
}
