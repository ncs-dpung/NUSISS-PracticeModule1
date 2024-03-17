package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.CustomerDto;
import com.nusiss.inventory.backend.service.CustomerService;
import io.swagger.annotations.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Api(value = "Customer Management", tags = "Customer Controller")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(
        value = "Get a list of all customers",
        authorizations = {@Authorization(value ="Bearer")})
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        List<CustomerDto> customers = customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }

    @ApiOperation(value = "Get a customer by ID")
    @ApiResponses(
        value = {
            @ApiResponse(code = 200, message = "Successfully retrieved customer"),
            @ApiResponse(code = 404, message = "Customer not found")
        })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(
        @ApiParam(value = "Customer ID", required = true) @PathVariable Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

    @ApiOperation(value = "Create a new customer")
    @PostMapping
    @Transactional
    public ResponseEntity<CustomerDto> createCustomer(
        @ApiParam(value = "Customer details", required = true) @RequestBody CustomerDto customerDto) {
        CustomerDto newCustomer = customerService.createCustomer(customerDto);
        return ResponseEntity.ok(newCustomer);
    }

    @ApiOperation(value = "Update a customer")
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
        @ApiParam(value = "Customer ID", required = true) @PathVariable Long id,
        @ApiParam(value = "Customer details", required = true) @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @ApiOperation(value = "Delete a customer")
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
        @ApiParam(value = "Customer ID", required = true) @PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation(
//        value = "Get a list of all customer orders",
//        authorizations = {@Authorization
//        (value = "Bearer")})
//    @GetMapping("/{id}/orders")
//    public ResponseEntity<List<OrderDto>> getCustomerOrders(
//        @ApiParam(value = "Customer ID", required = true) @PathVariable Long id) {
//        List<OrderDto> orders = customerService.getCustomerOrders(id);
//        return ResponseEntity.ok(orders);
//    }
//
//    @ApiOperation(
//        value = "Get a list of all customer invoices",
//        authorizations = {@Authorization
//        (value = "Bearer")})
//    @GetMapping("/{id}/invoices")
//    public ResponseEntity<List<InvoiceDto>> getCustomerInvoices(
//        @ApiParam(value = "Customer ID", required = true) @PathVariable Long id) {
//        List<InvoiceDto> invoices = customerService.getCustomerInvoices(id);
//        return ResponseEntity.ok(invoices);
//    }
}
