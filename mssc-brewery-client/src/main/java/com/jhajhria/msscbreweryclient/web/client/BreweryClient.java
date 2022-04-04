package com.jhajhria.msscbreweryclient.web.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "jhajhria.brewery", ignoreUnknownFields = false)
public class BreweryClient {

    public final String BEER_PATH_V1 = "/api/v1/beer";
    private String apiHost;

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getApiHost() {
        return apiHost;
    }
}