package com.taskmanager.taskmanager.infrastructure.config;

import com.taskmanager.taskmanager.application.port.in.TaskUseCase;
import com.taskmanager.taskmanager.application.port.out.TaskGateway;
import com.taskmanager.taskmanager.application.usecase.TaskInteractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public TaskUseCase taskUseCase(TaskGateway taskGateway) {
        return new TaskInteractor(taskGateway);
    }
}
