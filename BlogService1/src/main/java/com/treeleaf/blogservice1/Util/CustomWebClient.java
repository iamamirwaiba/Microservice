package com.treeleaf.blogservice1.Util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CustomWebClient {
    WebClient webClient;

    public CustomWebClient(){
            this.webClient= WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
        }
        public WebClient getWebclient(){
            return webClient;
        }

}
