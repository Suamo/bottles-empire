package com.suamo.bottlesfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class PlasticWarehouseController {
    private static final String FACTORY_URL = "http://bottles-factory/factory";

    @Autowired
    private RestTemplate rest;

    @PostMapping("/plastic")
    public String test(@RequestParam(value = "amount", defaultValue = "1") int amount) {
        String result = rest.postForObject(FACTORY_URL, amount, String.class);

        return "Plastic has been stashed. Amount: " + amount;
    }

}
