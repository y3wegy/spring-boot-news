package com.y3wegy.gateway.bean;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;

import java.util.Map;

public class LimitConfig {
    private String routeId;
    private Map<String, RedisRateLimiter.Config> tokenConfig;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Map<String, RedisRateLimiter.Config> getTokenConfig() {
        return tokenConfig;
    }

    public void setTokenConfig(Map<String, RedisRateLimiter.Config> tokenConfig) {
        this.tokenConfig = tokenConfig;
    }
}
