package com.taskmanager.taskmanager.application.port.in;

public record CreateTaskCommand(
        String title,
        String description,
        boolean completed
) {
}
