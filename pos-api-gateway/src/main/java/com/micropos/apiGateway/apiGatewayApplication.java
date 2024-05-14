package com.micropos.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class apiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(apiGatewayApplication.class);
    }
}