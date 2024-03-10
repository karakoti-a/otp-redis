package com.sms.sms.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password:}")
    private String password;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String,Object>redisTemplate (RedisConnectionFactory cf ){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer  serializer =  new StringRedisSerializer();

        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);

        return redisTemplate;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory cf ){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(cf);
        return  stringRedisTemplate;
    }

}
