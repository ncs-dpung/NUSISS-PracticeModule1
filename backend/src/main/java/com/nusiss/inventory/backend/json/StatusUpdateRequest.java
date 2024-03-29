package com.nusiss.inventory.backend.json;

import com.nusiss.inventory.backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusUpdateRequest {
    private OrderStatus status;
    private Date deliveryDate;
}
