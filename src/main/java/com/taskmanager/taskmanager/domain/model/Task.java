package com.taskmanager.taskmanager.domain.model;

public class Task {
    private final Long id;
    private final String title;
    private final String description;
    private final boolean completed;

    public Task(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public static Task create(String title, String description, boolean completed) {
        return new Task(null, title, description, completed);
    }

    public Task updateDetails(String title, String description, boolean completed) {
        return new Task(id, title, description, completed);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
