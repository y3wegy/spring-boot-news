package com.y3wegy.gateway.filter;

import com.y3wegy.base.web.bean.web.ResponseJson;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * all response format to {@link ResponseJson}
 * @author e631876
 */
@Component
public class ResponseJsonFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(ResponseJsonFilter.class);

    @Override
    public int getOrder() {
        // -1 is response write filter, must be called before that
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = serverResponse.bufferFactory();

        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(serverResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] bodyBytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bodyBytes);
                        DataBufferUtils.release(dataBuffer);
                        String bodyData = new String(bodyBytes, StandardCharsets.UTF_8);

                        ResponseJson responseJson = new ResponseJson(bodyData);
                        byte[] responseBytes = ResponseHelper.byteReponse(responseJson);

                        //如果不重新设置长度则收不到消息
                        serverResponse.getHeaders().setContentLength(responseBytes.length);
                        serverResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                        return bufferFactory.wrap(responseBytes);
                    }));
                }
                // if body is not a flux. never got there.
                return super.writeWith(body);
            }
        };

        // replace response with decorator
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }
}
