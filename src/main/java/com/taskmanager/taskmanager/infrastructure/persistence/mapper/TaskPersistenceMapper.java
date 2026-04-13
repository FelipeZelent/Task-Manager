package com.taskmanager.taskmanager.infrastructure.persistence.mapper;

import com.taskmanager.taskmanager.domain.model.Task;
import com.taskmanager.taskmanager.infrastructure.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskPersistenceMapper {
    public Task toDomain(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isCompleted()
        );
    }

    public TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setCompleted(task.isCompleted());
        return entity;
    }
}
