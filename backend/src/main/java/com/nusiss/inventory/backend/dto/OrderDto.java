package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
  private Long orderId;
  private Long staffId;
  private String staffFirstName;
  private String staffLastName;
  private Long customerId;
  private String customerName;
  private Date datePlaced;
  private Date dateShipped;
  private OrderStatus status;
  private Collection<OrderItemDto> items;

  public BigDecimal getTotal() {
    return items.stream().map(OrderItemDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
