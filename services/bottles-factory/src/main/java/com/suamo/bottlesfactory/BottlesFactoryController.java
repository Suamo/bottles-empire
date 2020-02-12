package com.suamo.bottlesfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding(Source.class)
@RestController
public class BottlesFactoryController {

    private static final int BOTTLE_PLASTIC_COST = 3;

    @Autowired
    private Source source;

    private int totalAmountOfPlastic = 0;

    @PostMapping("/factory")
    public String makeBottles(@RequestParam int amount) {
        totalAmountOfPlastic += amount;
        System.out.println("Received plastic: " + amount + ", total now: " + totalAmountOfPlastic);

        int newBottles = totalAmountOfPlastic / BOTTLE_PLASTIC_COST;
        if (newBottles > 0) {
            System.out.println("Processing " + (BOTTLE_PLASTIC_COST * newBottles) + " plastic for " + newBottles + " bottles.");
            source.output().send(MessageBuilder.withPayload(newBottles).setHeader("bottlesIncome", newBottles).build());
            totalAmountOfPlastic = totalAmountOfPlastic % BOTTLE_PLASTIC_COST;
            return "Send plastic to bottles factory. Excepted bottles: " + newBottles;
        }
        return "Received new plastic! Yeeey! Received: " + amount;
    }

}
