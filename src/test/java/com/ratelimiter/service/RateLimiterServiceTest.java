package com.ratelimiter.service;

import com.ratelimiter.config.RateLimiterConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RateLimiterServiceTest {

    private RateLimiterService rateLimiterService;

    @BeforeEach
    void setUp() {
        RateLimiterConfig config = new RateLimiterConfig();
        config.setMaxRequests(2); // Small limit for testing
        config.setWindowSizeSeconds(1);
        rateLimiterService = new RateLimiterService(config);
    }

    @Test
    void allowRequest_shouldLimitRequests() {
        String clientId = "test-client";
        
        assertTrue(rateLimiterService.allowRequest(clientId), "First request should be allowed");
        assertTrue(rateLimiterService.allowRequest(clientId), "Second request should be allowed");
        assertFalse(rateLimiterService.allowRequest(clientId), "Third request should be blocked (Max 2)");
    }
}
