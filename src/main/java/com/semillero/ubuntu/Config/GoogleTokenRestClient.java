package com.semillero.ubuntu.Config;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class GoogleTokenRestClient {
    private final RestClient restClient;
    public GoogleTokenRestClient(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://oauth2.googleapis.com")
                .build();
    }
    public Map<String,String> getTokenInfo(String token){

        return restClient.get()
                .uri("/tokeninfo?id_token={token}",token)
                .retrieve()
                .body(new ParameterizedTypeReference<Map<String, String>>() {
                });
    }
}
