package com.y3wegy.web.rpc.cloudservice;

import com.y3wegy.web.rpc.cloudservice.feignclient.UserFeignClient;
import com.y3wegy.web.rpc.cloudservice.feignclient.WebFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.y3wegy.web.rpc.cloudservice.fallback.UserFeignClientFallBack;
import com.y3wegy.web.rpc.cloudservice.fallback.WebFeignClientFallBack;

import feign.Client;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.HystrixFeign;

/**
 * @author y3wegy
 * in one project FeignClient service id muts only exists in one feignClient , so need build feign client manually
 */
@Configuration
@Import(FeignClientProperties.FeignClientConfiguration.class)
public class CloudServiceFeignBuilder {

    @Value("${eureka.service.url}")
    private String serviceURL;

    @Bean
    public UserFeignClient buildUserFeignClient(Client client) {
        return HystrixFeign.builder().client(client)
                .contract(new SpringMvcContract())
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(UserFeignClient.class, serviceURL + "/api/user", new UserFeignClientFallBack());
    }

    @Bean
    public WebFeignClient buildWebFeignClient(Client client) {
        return HystrixFeign.builder().client(client)
                .contract(new SpringMvcContract())
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "user"))
                .target(WebFeignClient.class, serviceURL + "/api/web", new WebFeignClientFallBack());
    }
}
