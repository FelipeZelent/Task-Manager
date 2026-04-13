package com.taskmanager.taskmanager.infrastructure.persistence.adapter;

import com.taskmanager.taskmanager.application.port.out.TaskGateway;
import com.taskmanager.taskmanager.domain.model.Task;
import com.taskmanager.taskmanager.infrastructure.persistence.mapper.TaskPersistenceMapper;
import com.taskmanager.taskmanager.infrastructure.persistence.repository.SpringDataTaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskRepositoryAdapter implements TaskGateway {
    private final SpringDataTaskRepository repository;
    private final TaskPersistenceMapper mapper;

    public TaskRepositoryAdapter(SpringDataTaskRepository repository, TaskPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Task> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Task save(Task task) {
        return mapper.toDomain(repository.save(mapper.toEntity(task)));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
