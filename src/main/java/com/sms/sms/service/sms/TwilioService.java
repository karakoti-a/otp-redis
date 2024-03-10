package com.sms.sms.service.sms;

import com.sms.sms.config.twilio.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {
    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getSid(), twilioConfig.getToken());
    }

    public void sendOTP(String phoneNumber, String body) {
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(twilioConfig.getNumber()),body
                        )
                .create();
    }
}
