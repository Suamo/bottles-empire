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

//    @StreamListener(Sink.INPUT)
//    public void log(Integer bottlesIncome) {
//        System.out.println("Test logging. Total: " + bottlesIncome + ". ");
//        processor.publishRequest(bottlesIncome);
//    }
//
    @StreamListener(target = Sink.INPUT, condition = "headers['bottlesIncome'] < 10")
    public void complain(Integer bottlesIncome) {
        String msg = "Another pack of bottles produced: " + bottlesIncome;
        System.out.println(msg);
        processor.publishRequest(msg);
    }

    @StreamListener(target = Sink.INPUT, condition = "headers['bottlesIncome'] >= 10")
    public void makeHype(Integer bottlesIncome) {
        String msg = "AA! So much bottles produced: " + bottlesIncome;
        System.out.println(msg);
        processor.publishRequest(msg);
    }

}
