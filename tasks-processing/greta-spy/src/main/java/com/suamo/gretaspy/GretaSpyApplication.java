package com.suamo.gretaspy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
public class GretaSpyApplication {

    @Autowired
    private TaskProcessor processor;

    public static void main(String[] args) {
        SpringApplication.run(GretaSpyApplication.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void log(Integer bottlesIncome) {
        System.out.println("Test logging. Total: " + bottlesIncome + ". ");
        processor.publishRequest(bottlesIncome);
    }

}
