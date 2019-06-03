package com.y3wegy.web.rpc.cloudservice;

import com.y3wegy.base.tools.JackSonHelper;
import com.y3wegy.web.rpc.cloudservice.fallback.UserFeignClientFallBack;
import com.y3wegy.web.rpc.cloudservice.fallback.WebFeignClientFallBack;
import com.y3wegy.web.rpc.cloudservice.feignclient.UserFeignClient;
import com.y3wegy.web.rpc.cloudservice.feignclient.WebFeignClient;
import feign.Client;
import feign.Logger;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author y3wegy
 * in one project FeignClient service id muts only exists in one feignClient , so need build feign client manually
 * <p>
 * !!!import if use feign default contract ,should  use @RequestLine in feign client
 */
@Configuration
@Import(FeignClientProperties.FeignClientConfiguration.class)
public class CloudServiceFeignBuilder {

    @Value("${eureka.service.url}")
    private String serviceURL;

    private JacksonDecoder decoder = new JacksonDecoder(JackSonHelper.getObjectMapper());
    private JacksonEncoder encoder = new JacksonEncoder(JackSonHelper.getObjectMapper());

    @Bean
    public UserFeignClient buildUserFeignClient(Client client) {

        return HystrixFeign.builder().client(client)
                .decoder(decoder)
                .encoder(encoder)
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .contract(new SpringMvcContract())
                //.requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(UserFeignClient.class, serviceURL + "/api/user", new UserFeignClientFallBack());
    }

    @Bean
    public WebFeignClient buildWebFeignClient(Client client) {
        return HystrixFeign.builder().client(client)
                .decoder(decoder)
                .encoder(encoder)
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .contract(new SpringMvcContract())
                //.requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(WebFeignClient.class, serviceURL + "/api/web", new WebFeignClientFallBack());
    }
}
