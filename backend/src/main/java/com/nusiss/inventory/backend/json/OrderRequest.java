package com.nusiss.inventory.backend.json;

import com.nusiss.inventory.backend.entity.OrderStatus;
import java.util.Collection;
import java.util.Date;
import lombok.*;

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
