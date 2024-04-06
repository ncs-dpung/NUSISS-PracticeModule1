package com.nusiss.inventory.backend.json;

import com.nusiss.inventory.backend.entity.OrderStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusUpdateRequest {
  private OrderStatus status;
  private Date deliveryDate;
}
