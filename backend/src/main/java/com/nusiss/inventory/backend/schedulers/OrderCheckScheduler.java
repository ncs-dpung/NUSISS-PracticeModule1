package com.nusiss.inventory.backend.schedulers;

import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.observers.impl.StaffPendingOrderNotificationObserver;
import com.nusiss.inventory.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class OrderCheckScheduler {

  @Autowired private OrderRepository orderRepository;

  @Autowired private StaffPendingOrderNotificationObserver staffPendingOrderNotificationObserver;

  // @Scheduled(fixedRate = 120000) // 24 hours: 86400000
  public void notifyStaffForOldPendingOrders() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -2); // Go back 2 days
    Date cutoffDate = calendar.getTime();

    List<Order> pendingOrders = orderRepository.findPendingOrdersOlderThanDays(cutoffDate);
    pendingOrders.forEach(staffPendingOrderNotificationObserver::notify);
  }
}
