package com.sms.sms.config.redis;

public class CacheKeys {
    public  static final String CACHE_OTP_KEY = "otp:";
    public  static final String CACHE_RATE_LIMIT_KEY = "rate:";

    public static String getCACHE_OTP_KEY() {
        return CACHE_OTP_KEY;
    }

    public static String getCACHE_RATE_LIMIT_KEY() {
        return CACHE_RATE_LIMIT_KEY;
    }
}
