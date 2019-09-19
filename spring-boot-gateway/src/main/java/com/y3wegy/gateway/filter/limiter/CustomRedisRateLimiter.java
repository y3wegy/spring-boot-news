package com.y3wegy.gateway.filter.limiter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.gateway.bean.LimitConfig;
import com.y3wegy.gateway.bean.LimitKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * custom {@link org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter}
 * @author e631876
 */
@ConfigurationProperties("spring.cloud.gateway.redis-rate-limiter")
public class CustomRedisRateLimiter extends AbstractRateLimiter<RedisRateLimiter.Config>
        implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(CustomRedisRateLimiter.class);

    /**
     * @deprecated use {@link RedisRateLimiter.Config#replenishRate}
     */
    @Deprecated
    public static final String REPLENISH_RATE_KEY = "replenishRate";

    /**
     * @deprecated use {@link RedisRateLimiter.Config#burstCapacity}
     */
    @Deprecated
    public static final String BURST_CAPACITY_KEY = "burstCapacity";

    /**
     * Redis Rate Limiter property name.
     */
    public static final String CONFIGURATION_PROPERTY_NAME = "redis-rate-limiter";

    /**
     * Redis Script name.
     */
    public static final String REDIS_SCRIPT_NAME = "redisRequestRateLimiterScript";

    /**
     * Remaining Rate Limit header name.
     */
    public static final String REMAINING_HEADER = "X-RateLimit-Remaining";

    /**
     * Replenish Rate Limit header name.
     */
    public static final String REPLENISH_RATE_HEADER = "X-RateLimit-Replenish-Rate";

    /**
     * Burst Capacity Header name.
     */
    public static final String BURST_CAPACITY_HEADER = "X-RateLimit-Burst-Capacity";

    private Log log = LogFactory.getLog(getClass());

    private ReactiveRedisTemplate<String, String> redisTemplate;

    private RedisScript<List<Long>> script;

    private AtomicBoolean initialized = new AtomicBoolean(false);

    private RedisRateLimiter.Config defaultConfig;

    // configuration properties
    /**
     * Whether or not to include headers containing rate limiter information, defaults to
     * true.
     */
    private boolean includeHeaders = true;

    /**
     * The name of the header that returns number of remaining requests during the current
     * second.
     */
    private String remainingHeader = REMAINING_HEADER;

    /** The name of the header that returns the replenish rate configuration. */
    private String replenishRateHeader = REPLENISH_RATE_HEADER;

    /** The name of the header that returns the burst capacity configuration. */
    private String burstCapacityHeader = BURST_CAPACITY_HEADER;

    public CustomRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate,
                                  RedisScript<List<Long>> script, Validator validator) {
        super(RedisRateLimiter.Config.class, CONFIGURATION_PROPERTY_NAME, validator);
        this.redisTemplate = redisTemplate;
        this.script = script;
        initialized.compareAndSet(false, true);
    }

    public CustomRedisRateLimiter(int defaultReplenishRate, int defaultBurstCapacity) {
        super(RedisRateLimiter.Config.class, CONFIGURATION_PROPERTY_NAME, null);
        this.defaultConfig = new RedisRateLimiter.Config().setReplenishRate(defaultReplenishRate)
                .setBurstCapacity(defaultBurstCapacity);
    }

    static List<String> getKeys(String id) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + id;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }

    public boolean isIncludeHeaders() {
        return includeHeaders;
    }

    public void setIncludeHeaders(boolean includeHeaders) {
        this.includeHeaders = includeHeaders;
    }

    public String getRemainingHeader() {
        return remainingHeader;
    }

    public void setRemainingHeader(String remainingHeader) {
        this.remainingHeader = remainingHeader;
    }

    public String getReplenishRateHeader() {
        return replenishRateHeader;
    }

    public void setReplenishRateHeader(String replenishRateHeader) {
        this.replenishRateHeader = replenishRateHeader;
    }

    public String getBurstCapacityHeader() {
        return burstCapacityHeader;
    }

    public void setBurstCapacityHeader(String burstCapacityHeader) {
        this.burstCapacityHeader = burstCapacityHeader;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (initialized.compareAndSet(false, true)) {
            this.redisTemplate = context.getBean("stringReactiveRedisTemplate",
                    ReactiveRedisTemplate.class);
            this.script = context.getBean(REDIS_SCRIPT_NAME, RedisScript.class);
            if (context.getBeanNamesForType(Validator.class).length > 0) {
                this.setValidator(context.getBean(Validator.class));
            }
        }
    }

    /* for testing */ RedisRateLimiter.Config getDefaultConfig() {
        return defaultConfig;
    }

    /**
     * This uses a basic token bucket algorithm and relies on the fact that Redis scripts
     * execute atomically. No other operations can run between fetching the count and
     * writing the new count.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Mono<Response> isAllowed(String routeId, String id) {
        if (!this.initialized.get()) {
            throw new IllegalStateException("RedisRateLimiter is not initialized");
        }

        LimitConfig limitConfig = getLimitConfig(routeId);

        if (limitConfig == null || limitConfig.getTokenConfig().size() == 0) {
            return Mono.just(new Response(true, null));
        }

        Map<String, RedisRateLimiter.Config> conf = limitConfig.getTokenConfig();

        LimitKey limitKey = null;
        try {
            limitKey = JackSonHelper.jsonStr2Obj(id, new TypeReference<LimitKey>() {
            });
        } catch (ServiceException e) {
            logger.error("parse LimitKey failed", e);
        }
        //api限流
        String api = limitKey.getApi();
        RedisRateLimiter.Config apiConf = conf.get(api);
        //业务方限流
        String biz = limitKey.getBiz();
        RedisRateLimiter.Config bizConf = conf.get(biz);

        if (apiConf != null) {
            return isSingleAllow(api, routeId, apiConf).flatMap(res -> {
                if (res.isAllowed()) {
                    if (bizConf != null) {
                        return isSingleAllow(biz, routeId, bizConf);
                    } else {
                        return Mono.just(new Response(true, new HashMap<>()));
                    }
                } else {
                    return Mono.just(res);
                }
            });
        } else {
            if (bizConf != null) {
                return isSingleAllow(biz, routeId, bizConf);
            } else {
                return Mono.just(new Response(true, new HashMap<>()));
            }
        }
    }

    /**
     * 单级限流
     * @param key
     * @param routeId
     * @param config
     * @return
     */
    private Mono<Response> isSingleAllow(String key, String routeId, RedisRateLimiter.Config config) {
        // How many requests per second do you want a user to be allowed to do?
        int replenishRate = config.getReplenishRate();

        // How much bursting do you want to allow?
        int burstCapacity = config.getBurstCapacity();

        try {
            List<String> keys = getKeys(routeId + "$" + key);

            // The arguments to the LUA script. time() returns unixtime in seconds.
            List<String> scriptArgs = Arrays.asList(replenishRate + "", burstCapacity + "",
                    Instant.now().getEpochSecond() + "", "1");
            // allowed, tokens_left = redis.eval(SCRIPT, keys, args)
            Flux<List<Long>> flux = this.redisTemplate.execute(this.script, keys, scriptArgs);
            // .log("redisratelimiter", Level.FINER);
            return flux.onErrorResume(throwable -> Flux.just(Arrays.asList(1L, -1L)))
                    .reduce(new ArrayList<Long>(), (longs, l) -> {
                        longs.addAll(l);
                        return longs;
                    }).map(results -> {
                        boolean allowed = results.get(0) == 1L;
                        Long tokensLeft = results.get(1);

                        Response response = new Response(allowed, getHeaders(config, tokensLeft));

                        if (log.isDebugEnabled()) {
                            log.debug("response: " + response);
                        }
                        return response;
                    });
        } catch (Exception e) {
            /*
             * We don't want a hard dependency on Redis to allow traffic. Make sure to set
             * an alert so you know if this is happening too much. Stripe's observed
             * failure rate is 0.01%.
             */
            log.error("Error determining if user allowed from redis", e);
        }
        return Mono.just(new Response(true, getHeaders(config, -1L)));
    }

    private LimitConfig getLimitConfig(String routeId) {
        Map<String, LimitConfig> map = new HashMap<>();
        LimitConfig limitConfig = new LimitConfig();
        limitConfig.setRouteId("rateLimit_route");
        Map<String, RedisRateLimiter.Config> tokenMap = new HashMap<>();
        RedisRateLimiter.Config apiConfig = new RedisRateLimiter.Config();
        apiConfig.setBurstCapacity(5);
        apiConfig.setReplenishRate(5);

        RedisRateLimiter.Config bizConfig = new RedisRateLimiter.Config();
        bizConfig.setBurstCapacity(1);
        bizConfig.setReplenishRate(1);

        tokenMap.put("/hello/rateLimit", apiConfig);
        tokenMap.put("jieyin", bizConfig);
        limitConfig.setTokenConfig(tokenMap);
        map.put("rateLimit_route", limitConfig);
        return limitConfig;
    }

    @NotNull
    public Map<String, String> getHeaders(RedisRateLimiter.Config config, Long tokensLeft) {
        Map<String, String> headers = new HashMap<>();
        if (isIncludeHeaders()) {
            headers.put(this.remainingHeader, tokensLeft.toString());
            headers.put(this.replenishRateHeader,
                    String.valueOf(config.getReplenishRate()));
            headers.put(this.burstCapacityHeader,
                    String.valueOf(config.getBurstCapacity()));
        }
        return headers;
    }
}
