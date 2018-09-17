package com.example.demo.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * redis设置
 */
@Configuration
@EnableCaching
public class RedisManagerConfig extends CachingConfigurerSupport {



}
