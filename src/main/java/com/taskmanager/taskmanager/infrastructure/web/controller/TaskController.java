package com.taskmanager.taskmanager.infrastructure.web.controller;

import com.taskmanager.taskmanager.application.port.in.TaskUseCase;
import com.taskmanager.taskmanager.infrastructure.web.dto.TaskRequest;
import com.taskmanager.taskmanager.infrastructure.web.dto.TaskResponse;
import com.taskmanager.taskmanager.infrastructure.web.mapper.TaskWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskUseCase taskUseCase;
    private final TaskWebMapper mapper;

    public TaskController(TaskUseCase taskUseCase, TaskWebMapper mapper) {
        this.taskUseCase = taskUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskUseCase.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return taskUseCase.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        TaskResponse response = mapper.toResponse(
                taskUseCase.create(mapper.toCreateCommand(request))
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest request) {
        return taskUseCase.update(id, mapper.toUpdateCommand(request))
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskUseCase.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
