package com.y3wegy.gateway.config;

import com.y3wegy.gateway.filter.limiter.CustomKeyResolver;
import com.y3wegy.gateway.filter.limiter.CustomRateLimiterGatewayFilterFactory;
import com.y3wegy.gateway.filter.limiter.CustomRedisRateLimiter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * @author e631876
 */
@Configuration
public class GatewayConfig {
    @Bean
    @Primary
    public CustomRedisRateLimiter customRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate,
                                                         @Qualifier(RedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> redisScript,
                                                         @Qualifier("webFluxValidator") Validator validator) {
        return new CustomRedisRateLimiter(redisTemplate, redisScript, validator);
    }

    @Bean
    public CustomKeyResolver createCustomKeyResolver() {
        return new CustomKeyResolver();
    }

    @Bean
    public CustomRateLimiterGatewayFilterFactory rateLimiterGatewayFilterFactory(CustomRedisRateLimiter customRedisRateLimiter, CustomKeyResolver customKeyResolver) {
        return new CustomRateLimiterGatewayFilterFactory(customRedisRateLimiter, customKeyResolver);
    }
}
