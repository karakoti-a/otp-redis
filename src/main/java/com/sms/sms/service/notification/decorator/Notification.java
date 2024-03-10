package com.sms.sms.service.notification.decorator;

import com.sms.sms.entity.Message;

public interface Notification {
    void sendNotification(Message message);
}
