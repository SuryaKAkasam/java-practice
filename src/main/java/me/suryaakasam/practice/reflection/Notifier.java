package me.suryaakasam.practice.reflection;

public class Notifier {
    private final NotificationService service;

    public Notifier(NotificationService service) {
        this.service = service;
    }

    public void notify(String user, String notification) {
        service.sendNotification(user, notification);
    }
}
