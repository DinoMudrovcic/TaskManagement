package com.dinomudrovcic.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TaskManagementApplication {

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(TaskManagementApplication.class);

        application.run(args);
    }

}
