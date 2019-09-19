package com.y3wegy.gateway.filter.limiter;


import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.gateway.bean.LimitKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * request rate limit key {@link LimitKey}
 * @author e631876
 */
public class CustomKeyResolver implements KeyResolver {
    private static final Logger logger = LoggerFactory.getLogger(CustomKeyResolver.class);

    public static final String BEAN_NAME = "customKeyResolver";

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(getKey(exchange));
    }

    /**
     *
     * @param exchange
     * @return
     */
    private String getKey(ServerWebExchange exchange) {

        LimitKey limitKey = new LimitKey();

        limitKey.setApi(exchange.getRequest().getPath().toString());
        limitKey.setBiz(exchange.getRequest().getQueryParams().getFirst("biz"));

        try {
            return JackSonHelper.obj2JsonStr(limitKey);
        } catch (ServiceException e) {
            logger.warn("parse limit key failed", e);
            return null;
        }
    }
}
