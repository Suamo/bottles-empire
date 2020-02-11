package com.suamo.bottlesfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class BottlesShopApplication {

    @Autowired
    private Counter counter;

    public static void main(String[] args) {
        SpringApplication.run(BottlesShopApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void log(Integer bottlesIncome) {
        counter.addBottles(bottlesIncome);
        System.out.println("Received " + bottlesIncome +
                " new bottles. Adding to the counter. Total: " + counter.getBottlesCount());
    }

}
