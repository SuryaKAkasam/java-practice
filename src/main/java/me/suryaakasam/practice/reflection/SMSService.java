package me.suryaakasam.practice.reflection;

import java.time.LocalDateTime;

public class SMSService implements NotificationService {
    @Override
    public void sendNotification(String recipient, String message) {
        System.out.println("[" + LocalDateTime.now() + "] SMS notification (message - '" + message + "') has been sent to " + recipient);
    }
}
