package com.nusiss.inventory.backend.dto;

import com.nusiss.inventory.backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private LocalDateTime datePlaced;
    private LocalDateTime dateShipped;
    private OrderStatus status;
    private Set<OrderItemDto> items;

    public BigDecimal getTotal() {
        return items.stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}