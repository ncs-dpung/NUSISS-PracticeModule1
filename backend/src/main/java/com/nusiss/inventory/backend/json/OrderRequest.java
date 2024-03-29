package com.nusiss.inventory.backend.json;

import com.nusiss.inventory.backend.dto.OrderItemDto;
import com.nusiss.inventory.backend.entity.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderRequest {
    // TODO: Uncomment the following line when user management is implemented
//    private Long userId;
    private Long customerId;
    private Date datePlaced;
    private Date dateShipped;
    private OrderStatus status;
    private Collection<OrderItemRequest> items;

}
