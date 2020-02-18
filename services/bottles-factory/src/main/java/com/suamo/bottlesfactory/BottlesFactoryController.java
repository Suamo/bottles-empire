package com.suamo.bottlesfactory;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@EnableBinding(Source.class)
@RestController
public class BottlesFactoryController extends WebSecurityConfigurerAdapter {

    private static final int BOTTLE_PLASTIC_COST = 3;

    @Autowired
    private Source source;

    private int totalAmountOfPlastic = 0;

    @HystrixCommand(fallbackMethod = "makeBottlesBackup")
    @PostMapping("/factory")
    public String makeBottles(@RequestParam int amount,
                              @RequestAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE") String accessToken) {
        randomFail();

        totalAmountOfPlastic += amount;
        System.out.println(accessToken);
        String msg = "Received plastic: " + amount + ", total now: " + totalAmountOfPlastic + ".";

        System.out.println(msg);

        int newBottles = totalAmountOfPlastic / BOTTLE_PLASTIC_COST;
        if (newBottles > 0) {
            System.out.println("Processing " + (BOTTLE_PLASTIC_COST * newBottles) + " plastic for " + newBottles + " bottles.");
            source.output().send(MessageBuilder.withPayload(newBottles).setHeader("bottlesIncome", newBottles).build());
            totalAmountOfPlastic = totalAmountOfPlastic % BOTTLE_PLASTIC_COST;
            return msg + " Sent " + (BOTTLE_PLASTIC_COST * newBottles) + " plastic to bottles factory. Excepted bottles: " + newBottles;
        }
        return msg;
    }

    public String makeBottlesBackup(@RequestParam int amount,
                                    @RequestAttribute("OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE") String accessToken,
                                    Throwable exception) {
        exception.printStackTrace();
        return "The factory is not available. Please come back later.";
    }

    private void randomFail() {
        if (new Random().nextInt(2) == 1) {
            throw new RuntimeException("Hello random fail!");
        }
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/actuator/hystrix.stream");
    }
}
