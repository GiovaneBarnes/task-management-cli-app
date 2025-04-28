package com.giovanebarnes.taskmanager.model;
import java.util.UUID;
import java.time.LocalDate;
import java.util.Optional;

public class Task {
    private UUID id;
    private LocalDate dueDate;
    private Optional<String> description;
    private boolean isCompleted;
    private String title;

    public UUID getId() {
        return id;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Task(String title, Optional<String> description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.id = UUID.randomUUID();
        this.isCompleted = false;
    }

    @Override
    public String toString(){
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description=" + description.orElse("No description") +
                ", dueDate=" + dueDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
