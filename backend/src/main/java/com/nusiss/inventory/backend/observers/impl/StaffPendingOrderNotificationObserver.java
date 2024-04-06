package com.nusiss.inventory.backend.observers.impl;

import com.nusiss.inventory.backend.entity.Order;
import com.nusiss.inventory.backend.observers.OrderStatusObserver;
import com.nusiss.inventory.backend.repository.OrderRepository;
import com.nusiss.inventory.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class StaffPendingOrderNotificationObserver implements OrderStatusObserver {

    private final NotificationService notificationService;

    @Autowired
    public StaffPendingOrderNotificationObserver(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void notify(Order order) {
        if ("Pending".equals(order.getStatus().getName()) && isOrderOlderThanDays(order, 2)) {
            String recipient = order.getStaff().getLastName() + order.getStaff().getFirstName();
            String subject = "Pending Order";
            String message = "Order " + order.getOrderId() + " has been pending for more than 2 days.";

            notificationService.sendNotification(recipient, subject, message);
        }
    }

    private boolean isOrderOlderThanDays(Order order, int days) {
        LocalDate orderDate = order.getDatePlaced().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return orderDate.plusDays(days).isBefore(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}

