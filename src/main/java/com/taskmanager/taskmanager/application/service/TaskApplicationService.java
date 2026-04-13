package com.taskmanager.taskmanager.application.service;

import com.taskmanager.taskmanager.application.port.in.CreateTaskCommand;
import com.taskmanager.taskmanager.application.port.in.TaskUseCase;
import com.taskmanager.taskmanager.application.port.in.UpdateTaskCommand;
import com.taskmanager.taskmanager.application.port.out.TaskGateway;
import com.taskmanager.taskmanager.domain.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskApplicationService implements TaskUseCase {
    private final TaskGateway taskGateway;

    public TaskApplicationService(TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }

    @Override
    public List<Task> findAll() {
        return taskGateway.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskGateway.findById(id);
    }

    @Override
    public Task create(CreateTaskCommand command) {
        Task task = Task.create(command.title(), command.description(), command.completed());
        return taskGateway.save(task);
    }

    @Override
    public Optional<Task> update(Long id, UpdateTaskCommand command) {
        return taskGateway.findById(id)
                .map(task -> task.updateDetails(command.title(), command.description(), command.completed()))
                .map(taskGateway::save);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!taskGateway.existsById(id)) {
            return false;
        }

        taskGateway.deleteById(id);
        return true;
    }
}
