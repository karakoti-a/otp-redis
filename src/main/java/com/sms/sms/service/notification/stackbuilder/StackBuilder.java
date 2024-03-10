package com.sms.sms.service.notification.stackbuilder;

import com.sms.sms.entity.Message;
import com.sms.sms.service.notification.decorator.Notification;
import com.sms.sms.service.notification.decorator.Notifier;
import com.sms.sms.service.notification.decorator.SmsNotificationDecorator;
import com.sms.sms.service.sms.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StackBuilder {

    private final String []prefer = {"sms","email"};

    @Autowired
    TwilioService twilioService;

    public Notification buildNotifyStack(String body, Message message){

        String [] preferences =  prefer;

        Notification notifyStack =  new Notifier();
        for(int i=0;i<preferences.length ;i++){
            if(preferences[i].equalsIgnoreCase("sms")){
                SmsNotificationDecorator smsDeco =  new SmsNotificationDecorator(notifyStack,twilioService);
                smsDeco.buildMessage(message,body);
                notifyStack = smsDeco;
            }

            if(preferences[i].equalsIgnoreCase("email")){
//                SmsNotificationDecorator smsDeco =  new SmsNotificationDecorator(notifyStack,twilioService);
//                smsDeco.buildMessage(message,body);
//                notifyStack = smsDeco;
            }
        }

        return notifyStack;
    }
}
