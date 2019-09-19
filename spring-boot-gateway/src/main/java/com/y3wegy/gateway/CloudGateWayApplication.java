package com.y3wegy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author e631876
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateWayApplication.class, args);
    }
}
