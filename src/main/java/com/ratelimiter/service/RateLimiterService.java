package com.ratelimiter.service;

import com.ratelimiter.config.RateLimiterConfig;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    
    private final RateLimiterConfig config;
    private final Map<String, UserRequestData> requestCounts = new ConcurrentHashMap<>();

    public RateLimiterService(RateLimiterConfig config) {
        this.config = config;
    }

    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        long windowSizeMillis = config.getWindowSizeSeconds() * 1000L;
        
        UserRequestData data = requestCounts.computeIfAbsent(clientId, k -> new UserRequestData(currentTime, 0));
        
        synchronized(data) {
             if (currentTime - data.startTime > windowSizeMillis) {
                // Reset window
                data.startTime = currentTime;
                data.requestCount = 1;
                return true;
            }
            
            if (data.requestCount < config.getMaxRequests()) {
                data.requestCount++;
                return true;
            }
            
            return false;
        }
    }

    private static class UserRequestData {
        long startTime;
        int requestCount;

        UserRequestData(long startTime, int requestCount) {
            this.startTime = startTime;
            this.requestCount = requestCount;
        }
    }
}
