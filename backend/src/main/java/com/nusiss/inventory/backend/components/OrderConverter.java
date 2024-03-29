package com.nusiss.inventory.backend.components;

import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.dto.OrderItemDto;
import com.nusiss.inventory.backend.entity.Customer;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.entity.OrderItem;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.repository.CustomerRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderConverter(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Order convertOrderDtoToEntity(OrderDto orderDto) {
        Order order = new Order();

        // Assuming ID is set only for updates. For new orders, ID should not be set.
        order.setOrderId(orderDto.getOrderId());

        // Set customer (assuming a valid customerId is part of OrderDto)
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + orderDto.getCustomerId()));
        order.setCustomer(customer);

        // Set other simple fields
        order.setDatePlaced(orderDto.getDatePlaced());
        order.setDateShipped(orderDto.getDateShipped());
        order.setStatus(orderDto.getStatus());

        // Handle OrderItems
        Set<OrderItem> items = orderDto.getItems().stream()
                .map(itemDto -> {
                    Product product = productRepository.findById(itemDto.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + itemDto.getProductId()));

                    OrderItem item = new OrderItem();
                    item.setOrder(order); // Set back-reference to the order
                    item.setProduct(product); // Assuming OrderItem has a reference to Product
                    item.setQuantity(itemDto.getQuantity());
                    item.setPrice(itemDto.getPrice());
                    return item;
                }).collect(Collectors.toSet());
        order.setItems(items);

        return order;
    }

    public OrderDto convertOrderToDto(Order order) {
        // Collect product IDs from order items
        Set<Long> productIds = order.getItems().stream()
                .map(OrderItem -> OrderItem.getProduct().getId())
                .collect(Collectors.toSet());

        // Fetch products in bulk and map by ID for quick access
        Map<Long, String> productIdToNameMap = productRepository.findByIdIn(productIds).stream()
                .collect(Collectors.toMap(Product::getId, Product::getName));

        // Convert order items to OrderItemDto, using the map to set product names
        Set<OrderItemDto> itemDtos = order.getItems().stream().map(item -> {
            String productName = productIdToNameMap.getOrDefault(item.getProduct().getId(), "Product Name Not Found");
            return new OrderItemDto(item.getProduct().getId(), productName, item.getQuantity(), item.getPrice());
        }).collect(Collectors.toSet());

        OrderDto dto = new OrderDto(
                order.getOrderId(),
                order.getUser().getId(),
                order.getCustomer().getId(),
                order.getCustomer().getCustomerName(),
                order.getDatePlaced(),
                order.getDateShipped(),
                order.getStatus(),
                itemDtos
        );
        // Note: The total is computed within the OrderDto itself.
        return dto;
    }

    public OrderItem convertOrderItemDtoToEntity(OrderItemDto itemDto, Order order) {
        OrderItem item = new OrderItem();
        Product product = productRepository.findById(itemDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + itemDto.getProductId()));
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(itemDto.getQuantity());
        item.setPrice(itemDto.getPrice());
        return item;
    }
}
