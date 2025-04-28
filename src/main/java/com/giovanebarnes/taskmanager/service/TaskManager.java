package com.giovanebarnes.taskmanager.service;
import com.giovanebarnes.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskManager {

    private List<Task> tasks;

    public TaskManager(){
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public void listAllTasks(){
        tasks.forEach(System.out::println);
    }

    public List<Task> listIncompleteTasks(){
        return tasks.stream().filter(task -> !task.isCompleted()).collect(Collectors.toList());
    }

    public void markTaskCompleted(UUID taskId){
        tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst().ifPresent(task -> task.setCompleted(true));
    }

    public void removeTask(UUID taskId){
        tasks.removeIf(task -> task.getId().equals(taskId));
    }

    public void sortByDueDate(){
        tasks = tasks.stream().sorted((t1,t2) -> t1.getDueDate().compareTo(t2.getDueDate())).collect(Collectors.toList());
    }
}
