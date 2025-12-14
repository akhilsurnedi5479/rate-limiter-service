package com.ratelimiter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "rate.limiter")
@Data
public class RateLimiterConfig {
    private int maxRequests = 10;
    private int windowSizeSeconds = 60;
}
