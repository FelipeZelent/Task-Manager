package com.taskmanager.taskmanager.infrastructure.persistence.repository;

import com.taskmanager.taskmanager.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, Long> {
}
