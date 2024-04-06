package com.nusiss.inventory.backend.service;

public interface NotificationService {
    void sendNotification(String recipient, String subject, String message);
}

