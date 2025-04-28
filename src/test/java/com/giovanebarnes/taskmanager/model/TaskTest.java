package com.giovanebarnes.taskmanager.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void taskCreationSetsCorrectFields() {
        String title = "Finish Homework";
        Optional<String> description = Optional.of("Math and Science");
        LocalDate dueDate = LocalDate.of(2029, 1, 18);

        Task task = new Task(title, description, dueDate);

        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(dueDate, task.getDueDate());
        assertFalse(task.isCompleted());
        assertNotNull(task.getId());
    }

    @Test
    void settingTaskCompletionWorks() {
        Task task = new Task("Buy groceries", Optional.empty(), LocalDate.now().plusDays(1));
        assertFalse(task.isCompleted());
        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }
}
