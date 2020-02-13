package com.suamo.bottlesfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpMethod.POST;

@EnableOAuth2Sso
@RestController
public class PlasticWarehouseController {
    private static final String FACTORY_URL = "http://bottles-factory/factory?amount=";

    @Autowired
    private OAuth2ClientContext clientContext;
    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    // "GET" just to be used from browser
    @GetMapping("/plastic")
    public String stash(@RequestParam(defaultValue = "1") int amount) {
        OAuth2AccessToken token = clientContext.getAccessToken();
        System.out.println("token: " + token.getValue());

        String url = FACTORY_URL + amount;
        oAuth2RestTemplate.exchange(url, POST, null, RequestEntity.class);

        return "Plastic has been stashed. Amount: " + amount;
    }

}
