package com.taskmanager.taskmanager.application.port.in;

import com.taskmanager.taskmanager.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskUseCase {
    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task create(CreateTaskCommand command);

    Optional<Task> update(Long id, UpdateTaskCommand command);

    boolean deleteById(Long id);
}
