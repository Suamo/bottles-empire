package com.suamo.bottlesfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableCircuitBreaker // https://martinfowler.com/bliki/CircuitBreaker.html
@EnableResourceServer
@SpringBootApplication
public class BottlesFactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BottlesFactoryApplication.class, args);
    }

}
