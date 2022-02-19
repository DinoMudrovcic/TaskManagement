package com.dinomudrovcic.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
public class TaskManagementApplication {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(TaskManagementApplication.class);

        application.run(args);
    }

}
