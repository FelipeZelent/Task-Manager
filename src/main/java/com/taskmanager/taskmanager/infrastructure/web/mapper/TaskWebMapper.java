package com.taskmanager.taskmanager.infrastructure.web.mapper;

import com.taskmanager.taskmanager.application.port.in.CreateTaskCommand;
import com.taskmanager.taskmanager.application.port.in.UpdateTaskCommand;
import com.taskmanager.taskmanager.domain.model.Task;
import com.taskmanager.taskmanager.infrastructure.web.dto.TaskRequest;
import com.taskmanager.taskmanager.infrastructure.web.dto.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskWebMapper {
    public CreateTaskCommand toCreateCommand(TaskRequest request) {
        return new CreateTaskCommand(
                request.title(),
                request.description(),
                request.completed()
        );
    }

    public UpdateTaskCommand toUpdateCommand(TaskRequest request) {
        return new UpdateTaskCommand(
                request.title(),
                request.description(),
                request.completed()
        );
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }
}
