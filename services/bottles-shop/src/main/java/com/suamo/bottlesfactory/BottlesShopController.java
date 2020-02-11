package com.suamo.bottlesfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BottlesShopController {

    @Autowired
    private Counter counter;

    @GetMapping
    public String list() {
        return "Bottles available: " + counter.getBottlesCount();
    }

    @PostMapping("buy")
    public String buy(@RequestParam int count) {
        if (counter.getBottlesCount() < count) {
            return "There are not enouth botles";
        }
        counter.substractBottles(count);
        return "Congrats! You have bought " + count + " bottles!";
    }

}
