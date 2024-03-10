package com.sms.sms.service;


import com.sms.sms.config.redis.CacheKeys;
import com.sms.sms.entity.Message;
import com.sms.sms.service.notification.decorator.Notification;

import com.sms.sms.service.notification.stackbuilder.StackBuilder;
import com.sms.sms.service.sms.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    @Autowired
    private TwilioService twilioService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringTemplate;

    @Autowired
    private StackBuilder stackBuilder;
    public String generateOtp(){
        // check rate limit
        if(isRateLimited(CacheKeys.getCACHE_RATE_LIMIT_KEY()+"1")){
            return "Request limit exceeded";
        }

        // check if otp already exist
            String existingOtp = (String) redisTemplate.opsForValue().get(CacheKeys.getCACHE_OTP_KEY()+"1");
            if(existingOtp!=null){
                return "Otp already exist . Please check your msgs";
            }

        // generate otp

        String newOtp = String.valueOf(this.generateRandomNumber());

        redisTemplate.opsForValue().set(CacheKeys.getCACHE_OTP_KEY()+"1", newOtp, Duration.ofMinutes(5));

        //build stack
        Message message = new Message();
        Notification notifyStack =  stackBuilder.buildNotifyStack(newOtp,message);

        //send
        notifyStack.sendNotification(message);

        return "Success";

    }
    private boolean isRateLimited(String rateLimit){
           Long reqNo =  redisTemplate.opsForValue().increment(rateLimit);

           if(reqNo == 1){
               redisTemplate.expire(rateLimit,5l, TimeUnit.MINUTES);
           }
           return reqNo > 3;
    }
    private int generateRandomNumber(){
       return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }
    private boolean verifyOtp(String payLoadOtp){
        String storedOtp =  (String) redisTemplate.opsForValue().get(CacheKeys.getCACHE_OTP_KEY()+"1");
        if(storedOtp!=null  && storedOtp.equals(payLoadOtp)){
            redisTemplate.delete(CacheKeys.getCACHE_OTP_KEY()+"1");
            return true;
        }
        return false;
    }


}
