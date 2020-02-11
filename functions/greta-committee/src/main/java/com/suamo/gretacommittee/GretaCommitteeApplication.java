package com.suamo.gretacommittee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@EnableTask
@SpringBootApplication
public class GretaCommitteeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GretaCommitteeApplication.class, args);
    }


    @Bean
    public PlasticAnalysisTask tollProcessingTask() {
        return new PlasticAnalysisTask();
    }

    public static class PlasticAnalysisTask implements CommandLineRunner {

        @Override
        public void run(String... args) {
            if (null != args) {
                System.out.println("Parameter length is " + args.length);

                String name = args[0];

                System.out.println("Hello" + name);
            }
        }

    }

}
