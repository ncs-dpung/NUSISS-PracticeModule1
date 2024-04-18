package com.nusiss.inventory.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nusiss.inventory.backend.entity.OrderItem;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nusiss.inventory.backend.components.OrderConverter;
import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.entity.OrderStatus;
import com.nusiss.inventory.backend.entity.Product;
import com.nusiss.inventory.backend.json.StatusUpdateRequest;
import com.nusiss.inventory.backend.observers.OrderStatusObserver;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.repository.OrderStatusRepository;
import com.nusiss.inventory.backend.repository.ProductRepository;
import com.nusiss.inventory.backend.service.impl.OrderServiceImpl;
import com.nusiss.inventory.backend.strategies.OrderUpdateStrategy;

import java.math.BigDecimal;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

  @Mock private OrderRepository orderRepository;
  @Mock private OrderStatusRepository orderStatusRepository;
  @Mock private ProductRepository productRepository;
  @Mock private OrderConverter orderConverter;
  @Mock private Map<String, OrderUpdateStrategy> updateStrategies;
  @Mock private List<OrderStatusObserver> observers;

  private OrderServiceImpl orderService;

  @BeforeEach
  public void setUp() {
    observers = new ArrayList<>();
    updateStrategies = new HashMap<>();

    orderService =
        new OrderServiceImpl(
            orderConverter,
            orderRepository,
            orderStatusRepository,
            productRepository,
            updateStrategies,
            observers);
  }

  @Test
  void testFindById_ThrowsEntityNotFoundException() {
    Long orderId = 1L;
    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> orderService.findById(orderId));
  }

  @Test
  void testCreateOrder() {
    OrderDto orderDto = new OrderDto();
    Order order = new Order();
    Product product = new Product();
    product.setId(1L);
    product.setQuantityAvailable(10);

    OrderItem item = new OrderItem(null, order, product, 5, new BigDecimal("100.00"));
    List<OrderItem> items = Arrays.asList(item);
    order.setItems(items);

    when(orderConverter.convertOrderDtoToEntity(orderDto)).thenReturn(order);
    when(productRepository.findById(any())).thenReturn(Optional.of(product));
    when(orderRepository.save(any(Order.class))).thenReturn(order);
    when(orderConverter.convertOrderToDto(any(Order.class))).thenReturn(orderDto);

    OrderDto createdOrder = orderService.createOrder(orderDto);

    assertNotNull(createdOrder);
    verify(productRepository).save(product);
  }

  @Test
  public void testUpdateOrderStatus_Processing() {
    // Arrange
    Long orderId = 1L;
    StatusUpdateRequest statusUpdateRequest =
        new StatusUpdateRequest(new OrderStatus(2L, "Delivered"), new Date());
    Order order = new Order();
    order.setOrderId(orderId);
    order.setStatus(new OrderStatus(1L, "Processing"));
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
    when(orderStatusRepository.findById(anyLong()))
        .thenReturn(Optional.of(new OrderStatus(2L, "Delivered")));
    when(orderRepository.save(any(Order.class))).thenReturn(order);
    when(orderConverter.convertOrderToDto(order)).thenReturn(new OrderDto());

    // Act
    OrderDto updatedOrderDto = orderService.updateOrderStatus(orderId, statusUpdateRequest);

    // Assert
    verify(orderRepository).save(order);
    assertEquals("Delivered", order.getStatus().getName());
    assertNotNull(updatedOrderDto);
  }

  @Test
  void testUpdateOrderStatus_Delivered() {
    Long orderId = 1L;
    OrderStatus deliveredStatus = new OrderStatus(2L, "Delivered");
    StatusUpdateRequest statusUpdateRequest = new StatusUpdateRequest(deliveredStatus, new Date());

    Order order = new Order();
    order.setOrderId(orderId);
    order.setStatus(new OrderStatus(1L, "Pending")); // Initial status

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
    when(orderStatusRepository.findById(deliveredStatus.getId()))
        .thenReturn(Optional.of(deliveredStatus));
    when(orderRepository.save(any(Order.class))).thenReturn(order);
    when(orderConverter.convertOrderToDto(order)).thenReturn(new OrderDto());

    OrderDto result = orderService.updateOrderStatus(orderId, statusUpdateRequest);

    assertNotNull(result);
    assertEquals("Delivered", order.getStatus().getName());
    verify(orderRepository).save(order);
    verify(orderConverter).convertOrderToDto(order);
  }

  @Test
  void testUpdateOrderStatus_NotFound() {
    Long orderId = 1L;
    StatusUpdateRequest statusUpdateRequest =
        new StatusUpdateRequest(new OrderStatus(2L, "Delivered"), new Date());

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(
        EntityNotFoundException.class,
        () -> orderService.updateOrderStatus(orderId, statusUpdateRequest));
  }

  @Test
  void testFindAllOrders() {
    List<Order> orders = Arrays.asList(new Order(), new Order());
    when(orderRepository.findAll()).thenReturn(orders);
    when(orderConverter.convertOrderToDto(any(Order.class))).thenReturn(new OrderDto());

    List<OrderDto> result = orderService.findAllOrders();

    assertNotNull(result);
    assertEquals(2, result.size());
    verify(orderRepository).findAll();
    verify(orderConverter, times(2)).convertOrderToDto(any(Order.class));
  }

  @Test
  void testUpdateOrder_NoStrategyFound() {
    Long orderId = 1L;
    OrderDto orderDto = new OrderDto();
    Order order = new Order();
    order.setOrderId(orderId);
    order.setStatus(new OrderStatus(1L, "UnknownStatus"));

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    assertThrows(IllegalStateException.class, () -> orderService.updateOrder(orderId, orderDto));
  }

  @Test
  void testDeleteOrder_Success() {
    Long orderId = 1L;
    Order order = new Order();
    order.setOrderId(orderId);
    order.setStatus(new OrderStatus(1L, "Pending"));
    order.setItems(new ArrayList<>());

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    assertDoesNotThrow(() -> orderService.deleteOrder(orderId));
    verify(orderRepository).delete(order);
  }

  @Test
  void testDeleteOrder_IllegalState() {
    Long orderId = 1L;
    Order order = new Order();
    order.setOrderId(orderId);
    order.setStatus(new OrderStatus(1L, "Processed"));

    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    assertThrows(IllegalStateException.class, () -> orderService.deleteOrder(orderId));
  }
}
