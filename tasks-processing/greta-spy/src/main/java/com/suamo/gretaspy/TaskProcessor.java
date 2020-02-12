package com.suamo.gretaspy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@EnableBinding(Source.class)
public class TaskProcessor {

    @Value("${maven.task.url}")
    private String mavenUrl;

    @Autowired
    private Source source;

    public void publishRequest(Integer bottlesIncome) {
        List<String> input = Collections.singletonList("" + bottlesIncome);
        TaskLaunchRequest request = new TaskLaunchRequest(mavenUrl, input, null, null, null);

        System.out.println("created task request");

        GenericMessage<TaskLaunchRequest> message = new GenericMessage<>(request);
        this.source.output().send(message);
    }
}
