package com.treeleaf.springgateway.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.DateTimeException;

@Configuration
public class SpringCloudGatewayConfig{


    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/user/**")
                        .uri("http://localhost:8081/"))
                .route(p -> p
                        .path("/blog/**")
                        .uri("http://localhost:8082/"))
                .route(p -> p
                        .path("/comment/**")
                        .uri("http://localhost:8082/"))
                .route(p -> p
                        .path("/app/chat")
                        .uri("http://localhost:8083/"))
                .build();
    }


}