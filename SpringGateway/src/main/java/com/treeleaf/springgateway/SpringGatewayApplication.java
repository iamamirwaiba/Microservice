package com.treeleaf.springgateway;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringGatewayApplication {


    @Bean
    @Qualifier("customWebClient")
    public WebClient customWebClient(){
        return WebClient.create();
    }

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringGatewayApplication.class, args);
    }

}
