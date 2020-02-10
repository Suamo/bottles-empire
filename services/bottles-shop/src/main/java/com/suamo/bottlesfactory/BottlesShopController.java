package com.suamo.bottlesfactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController // allows POST http://localhost:8081/actuator/refresh
public class BottlesShopController {

    @Value("${eureka.instance.instance-id}")
    private String myTestProperty;

    @GetMapping("/")
    public String test() {
        return myTestProperty;
    }

}
