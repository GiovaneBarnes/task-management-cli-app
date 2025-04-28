package com.giovanebarnes.taskmanager.cli;

import com.giovanebarnes.taskmanager.model.Task;
import com.giovanebarnes.taskmanager.service.TaskManager;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class TaskApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to the Task Manager CLI!");

        while (running) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTaskFlow();
                    break;
                case "2":
                    taskManager.listAllTasks();
                    break;
                case "3":
                    completeTaskFlow();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting Task Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Add a new Task");
        System.out.println("2. View all Tasks");
        System.out.println("3. Complete a Task");
        System.out.println("4. Exit");
        System.out.print("Your choice: ");
    }

    private static void addTaskFlow() {
        System.out.print("Enter Task Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Task Description (optional, press Enter to skip): ");
        String descInput = scanner.nextLine();
        Optional<String> description = descInput.isEmpty() ? Optional.empty() : Optional.of(descInput);

        System.out.print("Enter Due Date (YYYY-MM-DD): ");
        String dueDateInput = scanner.nextLine();
        LocalDate dueDate = LocalDate.parse(dueDateInput);

        Task newTask = new Task(title, description, dueDate);
        taskManager.addTask(newTask);

        System.out.println("Task added successfully!");
    }

    private static void completeTaskFlow() {
        System.out.print("Enter the ID of the Task to complete: ");
        String idInput = scanner.nextLine();

        try {
            UUID id = UUID.fromString(idInput);
            taskManager.markTaskCompleted(id);
            System.out.println("Task marked as completed!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format. Please try again.");
        }
    }
}
