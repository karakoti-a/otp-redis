package com.sms.sms.controller;

import com.sms.sms.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    OtpService otpService;
    @RequestMapping(value = "/test")
    public String test(){
        return "";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generate(){
        String x = otpService.generateOtp();
        return x;
    }

}
