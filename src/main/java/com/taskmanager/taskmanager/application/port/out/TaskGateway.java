package com.taskmanager.taskmanager.application.port.out;

import com.taskmanager.taskmanager.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskGateway {
    List<Task> findAll();

    Optional<Task> findById(Long id);

    Task save(Task task);

    boolean existsById(Long id);

    void deleteById(Long id);
}
