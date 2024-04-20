package com.nusiss.inventory.backend.controllers;

import com.nusiss.inventory.backend.dto.OrderDto;
import com.nusiss.inventory.backend.json.StatusUpdateRequest;
import com.nusiss.inventory.backend.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @ApiOperation(value = "Create a new order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Order created successfully"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
    OrderDto createdOrder = orderService.createOrder(orderDto);
    return ResponseEntity.ok(createdOrder);
  }

  @ApiOperation(value = "Update an existing order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Order updated successfully"),
        @ApiResponse(
            code = 500,
            message =
                "Unable to update order due to validation error or order not in PENDING status"),
        @ApiResponse(code = 404, message = "Order not found")
      })
  @PutMapping("/{orderId}")
  public ResponseEntity<OrderDto> updateOrder(
      @PathVariable Long orderId, @RequestBody OrderDto orderDto) {
    OrderDto updatedOrder = orderService.updateOrder(orderId, orderDto);
    return ResponseEntity.ok(updatedOrder);
  }

  @ApiOperation(value = "Get order by ID")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Order found"),
        @ApiResponse(code = 404, message = "Order not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @GetMapping("/{orderId}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
    OrderDto order = orderService.findById(orderId);
    return ResponseEntity.ok(order);
  }

  @ApiOperation(value = "Delete an order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Order deleted successfully"),
        @ApiResponse(code = 500, message = "Order cannot be deleted due to non-PENDING status"),
        @ApiResponse(code = 404, message = "Order not found")
      })
  @DeleteMapping("/{orderId}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
    orderService.deleteOrder(orderId);
    return ResponseEntity.ok().build();
  }

  @ApiOperation(value = "Update the status of an order")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Order status updated successfully"),
        @ApiResponse(
            code = 400,
            message = "Unable to update status due to validation error or incorrect order status"),
        @ApiResponse(code = 404, message = "Order or status not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @PutMapping("/{orderId}/status")
  public ResponseEntity<OrderDto> updateOrderStatus(
      @PathVariable Long orderId, @RequestBody StatusUpdateRequest statusUpdate) {
    OrderDto updatedOrder = orderService.updateOrderStatus(orderId, statusUpdate);
    return ResponseEntity.ok(updatedOrder);
  }

  @ApiOperation(value = "List all orders with status 'Pending' or 'Processed'")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Orders retrieved successfully"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @GetMapping("/pending-processed")
  public ResponseEntity<List<OrderDto>> getPendingAndProcessedOrders() {
    List<OrderDto> orders = orderService.findPendingAndProcessedOrdersSorted();
    return ResponseEntity.ok(orders);
  }

  @ApiOperation(value = "List all orders")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Orders retrieved successfully"),
        @ApiResponse(code = 500, message = "Internal Server Error")
      })
  @GetMapping("/all")
  public ResponseEntity<List<OrderDto>> getAllOrders() {
    List<OrderDto> orders = orderService.findAllOrders();
    return ResponseEntity.ok(orders);
  }
}
