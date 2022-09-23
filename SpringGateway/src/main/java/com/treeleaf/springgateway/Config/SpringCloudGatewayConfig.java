package com.treeleaf.springgateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SpringCloudGatewayConfig{

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/user/**")
                        .uri("http://localhost:8081"))
                .route(p -> p
                        .path("/blog/**")
                        .uri("http://localhost:8082"))
                .route(p -> p
                        .path("/comment/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}