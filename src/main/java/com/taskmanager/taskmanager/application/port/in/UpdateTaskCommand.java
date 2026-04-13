package com.taskmanager.taskmanager.application.port.in;

public record UpdateTaskCommand(
        String title,
        String description,
        boolean completed
) {
}
