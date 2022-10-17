package com.treeleaf.springgateway.Filter;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.treeleaf.springgateway.Entity.Blog;
import com.treeleaf.springgateway.Entity.LoginCredentials;
import lombok.ToString;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Configuration
public class GatewayFilter implements GlobalFilter {

    @Qualifier("customWebClient")
    private WebClient webClient;

    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    GatewayFilter(WebClient webClient,RestTemplate restTemplate){
        this.webClient = webClient;
        this.restTemplate=restTemplate;
    }


    final Logger logger =
            LoggerFactory.getLogger(GatewayFilter.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String URL= String.valueOf(exchange.getRequest().getURI());
        String endpoint=URL.substring(URL.length()-6,URL.length());
        if(endpoint.equals("signIn")||endpoint.equals("signUp")){
            return chain.filter(exchange);
        }
        String token= Objects.requireNonNull(exchange.getRequest().getHeaders().get("Authorization")).toString();
        String accessToken=token.substring(1,token.length()-1);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION,accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> value = restTemplate.exchange("http://localhost:8081/jwt/validate",HttpMethod.POST,entity,Map.class);
        Map<String,String> body=value.getBody();
        if(body.get("status").equals("false")){
            return this.onError(exchange,HttpStatus.UNAUTHORIZED);
        }


        else {
            logger.info("Prefilter");
        }
        return chain.filter(
                exchange.mutate().request(
                                exchange.getRequest().mutate()
                                        .header("Authentication", body.get("token"))
                                        .build())
                        .build());    }


    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus)  {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
