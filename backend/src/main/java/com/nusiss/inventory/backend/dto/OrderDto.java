package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private Long orderId;
    // TODO: Uncomment the following line when user management is implemented
//    private Long userId;
    private Long customerId;
    private String customerName;
    private Date datePlaced;
    private Date dateShipped;
    private OrderStatus status;
    private Collection<OrderItemDto> items;

    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}