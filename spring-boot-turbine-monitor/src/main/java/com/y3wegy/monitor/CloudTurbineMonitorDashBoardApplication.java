package com.y3wegy.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author y3wegy
 */
@SpringBootApplication
@EnableTurbine
public class CloudTurbineMonitorDashBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudTurbineMonitorDashBoardApplication.class, args);
    }
}