package com.taskmanager.taskmanager.infrastructure.web.dto;

public record TaskRequest(
        String title,
        String description,
        boolean completed
) {
}
