package com.sms.sms.service.notification.decorator;


import com.sms.sms.entity.Message;
import com.sms.sms.service.sms.TwilioService;



public class SmsNotificationDecorator extends NotificationDecorator{

    private TwilioService twilioService;


    public SmsNotificationDecorator(Notification notifier,TwilioService twilioService ) {

        super(notifier);
        this.twilioService = twilioService;
    }

    @Override
    public void sendNotification(Message message) {
        super.sendNotification(message);
        message.setPhoneNumber("+918800742476");
        sendSms(message);
    }
    private void sendSms(Message message){

        twilioService.sendOTP(message.getPhoneNumber(),message.getBody());
    }

    public void buildMessage(Message message,String body){
        message.setPhoneNumber("+918800742476");
        message.setBody("your otp is :- " +  body);
    }
}
