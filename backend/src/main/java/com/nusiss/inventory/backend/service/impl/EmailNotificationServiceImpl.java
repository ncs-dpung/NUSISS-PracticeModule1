package com.nusiss.inventory.backend.service.impl;

import com.nusiss.inventory.backend.service.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailNotificationServiceImpl implements NotificationService {
    /**
     * Sends an email notification to the recipient.
     *
     * @param recipient the recipient of the email
     * @param subject   the subject of the email
     * @param message   the message of the email
     */
   /* private final JavaMailSender mailSender;

    @Autowired
    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }*/

    @Override
    public void sendNotification(String recipient, String subject, String message) {
        /*
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email); */

        System.out.println("Email sent to " + recipient + " with subject: " + subject);
        System.out.println("Message: " + message);
    }
}
