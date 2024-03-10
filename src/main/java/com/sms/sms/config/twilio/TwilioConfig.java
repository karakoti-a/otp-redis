package com.sms.sms.config.twilio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    @Value("${twilio.account-sid}")
    private String sid;

    @Value("${twilio.auth-token}")
    private String token;

    @Value("${twilio.phone-number}")
    private String number;

    public String getSid() {
        return sid;
    }

    public String getToken() {
        return token;
    }

    public String getNumber() {
        return number;
    }

}
