package com.sms.sms.service.notification.decorator;

import com.sms.sms.entity.Message;

public class Notifier implements Notification{
    @Override
    public void sendNotification(Message message) {
        System.out.println("Sending msg from Base notifier " + message.getBody());
    }
}
