package com.example.base.batch.job;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "job", name = "name", havingValue = DeleteUserJob.JOB_NAME)
public class DeleteUserJob implements ApplicationRunner {

    public static final String JOB_NAME = "DELETE_USER_JOB";

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Hello World");
    }
}
