package com.suamo.bottlesfactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BottlesFactoryController {
    private int totalAmountOfPlastic = 0;

    @PostMapping("/factory")
    public void makeBottles(@RequestParam int amount) {
        totalAmountOfPlastic += amount;

        System.out.println("publish to rabbit mq here");
    }

}
