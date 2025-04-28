package com.giovanebarnes.taskmanager.service;

import com.giovanebarnes.taskmanager.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @Test
    void addingTaskStoresItCorrectly() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Test Task", Optional.empty(), LocalDate.now().plusDays(1));
        taskManager.addTask(task);

        assertEquals(1, taskManager.getTasks().size());
        assertEquals(task.getTitle(), taskManager.getTasks().getFirst().getTitle());
    }

    @Test
    void markingTaskAsCompletedWorks() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Complete Assignment", Optional.empty(), LocalDate.now());
        taskManager.addTask(task);

        UUID id = task.getId();
        taskManager.markTaskCompleted(id);

        assertTrue(taskManager.getTasks().getFirst().isCompleted());
    }

    @Test
    void removingTaskWorksCorrectly() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Delete Me", Optional.empty(), LocalDate.now());
        taskManager.addTask(task);

        UUID id = task.getId();
        taskManager.removeTask(id);

        assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    void listIncompleteTasksWorks(){
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Test Task", Optional.empty(), LocalDate.now().plusDays(1));
        taskManager.addTask(task1);

        task1.setCompleted(true);

        assertEquals(0, taskManager.listIncompleteTasks().size());
    }

    @Test
    void sortByDueDateWorks(){
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Test Task", Optional.empty(), LocalDate.now().plusDays(1));
        taskManager.addTask(task1);

        Task task2 = new Task("Test Task2", Optional.empty(), LocalDate.now().plusDays(5));
        taskManager.addTask(task2);

        Task task3 = new Task("Test Task3", Optional.empty(), LocalDate.now().plusDays(3));
        taskManager.addTask(task3);

        Task task4 = new Task("Test Task4", Optional.empty(), LocalDate.now().plusDays(2));
        taskManager.addTask(task4);

        taskManager.sortByDueDate();
        List<Task> sortedTasks = taskManager.getTasks();


        assertEquals(task1.getDueDate(), sortedTasks.get(0).getDueDate());
        assertEquals(task4.getDueDate(), sortedTasks.get(1).getDueDate());
        assertEquals(task3.getDueDate(), sortedTasks.get(2).getDueDate());
        assertEquals(task2.getDueDate(), sortedTasks.get(3).getDueDate());
    }

}
