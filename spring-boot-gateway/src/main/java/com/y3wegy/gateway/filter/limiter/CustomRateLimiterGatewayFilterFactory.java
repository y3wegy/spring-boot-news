package com.y3wegy.gateway.filter.limiter;

import com.y3wegy.base.exception.ServiceException;
import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.base.web.bean.web.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * overwrite {@link org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory}
 * @author e631876
 */
@Configuration
@ConfigurationProperties("spring.cloud.gateway.filter.request-rate-limiter")
public class CustomRateLimiterGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomRateLimiterGatewayFilterFactory.Config> {
    private static final Logger logger = LoggerFactory.getLogger(CustomRateLimiterGatewayFilterFactory.class);

    private static final String KEY_RESOLVER_KEY = "keyResolver";
    private static final String LIMIT_MSG = "please wait seconds and try again";

    private final RateLimiter defaultRateLimiter;
    private final KeyResolver defaultKeyResolver;

    public CustomRateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter,
                                                 KeyResolver defaultKeyResolver) {
        super(Config.class);
        this.defaultRateLimiter = defaultRateLimiter;
        this.defaultKeyResolver = defaultKeyResolver;
    }


    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = (config.keyResolver == null) ? defaultKeyResolver : config.keyResolver;
        RateLimiter<Object> limiter = (config.rateLimiter == null) ? defaultRateLimiter : config.rateLimiter;

        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

            return resolver.resolve(exchange).flatMap(key ->
                    // TODO: if key is empty?
                    limiter.isAllowed(route.getId(), key).flatMap(response -> {

                        for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                            exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                        }

                        if (response.isAllowed()) {
                            return chain.filter(exchange);
                        }
                        ServerHttpResponse serverHttpResponse = exchange.getResponse();
                        ResponseJson responseJson = new ResponseJson();
                        responseJson.code(String.valueOf(HttpStatus.TOO_MANY_REQUESTS.value()));
                        responseJson.fail(LIMIT_MSG);
                        byte[] datas = new byte[0];
                        try {
                            datas = JackSonHelper.obj2JsonStr(responseJson).getBytes(StandardCharsets.UTF_8);
                        } catch (ServiceException e) {
                            logger.error("response parse failed", e);
                        }
                        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(datas);
                        serverHttpResponse.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                        serverHttpResponse.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
                        return serverHttpResponse.writeWith(Mono.just(buffer));
                    }));
        };
    }


    public KeyResolver getDefaultKeyResolver() {
        return defaultKeyResolver;
    }

    public RateLimiter getDefaultRateLimiter() {
        return defaultRateLimiter;
    }

    public static class Config {
        private KeyResolver keyResolver;
        private RateLimiter rateLimiter;
        private HttpStatus statusCode = HttpStatus.TOO_MANY_REQUESTS;

        public KeyResolver getKeyResolver() {
            return keyResolver;
        }

        public Config setKeyResolver(KeyResolver keyResolver) {
            this.keyResolver = keyResolver;
            return this;
        }

        public RateLimiter getRateLimiter() {
            return rateLimiter;
        }

        public Config setRateLimiter(RateLimiter rateLimiter) {
            this.rateLimiter = rateLimiter;
            return this;
        }

        public HttpStatus getStatusCode() {
            return statusCode;
        }

        public Config setStatusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }
    }

}