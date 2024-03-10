package com.sms.sms.service.notification.decorator;

import com.sms.sms.entity.Message;

public class NotificationDecorator implements Notification{
    Notification notifier;

    public NotificationDecorator(Notification notifier) {
        this.notifier = notifier;
    }

    @Override
    public void sendNotification(Message message) {
        notifier.sendNotification(message);
    }
}
