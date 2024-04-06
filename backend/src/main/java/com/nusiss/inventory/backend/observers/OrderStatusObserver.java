package com.nusiss.inventory.backend.observers;

import com.nusiss.inventory.backend.entity.Order;

public interface OrderStatusObserver {
    void notify(Order order);
}
