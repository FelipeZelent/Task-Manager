package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
