package com.y3wegy.gateway.filter;

import com.y3wegy.base.web.bean.web.ResponseJson;
import com.y3wegy.base.web.bean.web.constants.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * validate
 * @author e631876
 */
@Component
public class AuthFilter implements GlobalFilter {
    private final String INVALID_USER = "unauthorized";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(WebConstants.TOKEN);
        if (StringUtils.isNotBlank(token)) {
            return chain.filter(exchange);
        }

        ResponseJson responseJson = new ResponseJson();
        responseJson.fail(String.valueOf(HttpStatus.UNAUTHORIZED.value()), INVALID_USER);
        byte[] responseBytes = ResponseHelper.byteReponse(responseJson);

        ServerHttpResponse serverResponse = exchange.getResponse();
        DataBuffer buffer = serverResponse.bufferFactory().wrap(responseBytes);
        serverResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        serverResponse.getHeaders().setContentLength(responseBytes.length);
        //"application/json;charset=UTF-8"
        serverResponse.getHeaders().add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return serverResponse.writeWith(Mono.just(buffer));
    }
}