package com.nusiss.inventory.backend.response;

import com.nusiss.inventory.backend.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StatusUpdateRequest {
    private OrderStatus status;
    private LocalDateTime deliveryDate;

}
