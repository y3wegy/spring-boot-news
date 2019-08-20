package com.y3wegy.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @author e631876
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class CloudAdminMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudAdminMonitorApplication.class, args);
    }

}
