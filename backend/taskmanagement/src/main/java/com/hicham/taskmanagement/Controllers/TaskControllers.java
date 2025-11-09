package com.hicham.taskmanagement.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Services.TaskServices;

@CrossOrigin("*")
@RestController
public class TaskControllers {
    public TaskServices taskServices;

    public TaskControllers(TaskServices taskServices) {
        this.taskServices = taskServices;
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskServices.getTasks();
    }

    @GetMapping("/task/{id}")
    public Task getTaskById(@PathVariable (value = "id") Long id) {
        return taskServices.getTaskById(id);
    }

    @GetMapping("/task")
    public Task getTaskByIdRequestParam(@RequestParam Long id) {
        return taskServices.getTaskById(id);
    }

    @PostMapping("/addingTask")
    public void saveTask(@RequestBody Task task) {
        taskServices.saveTask(task);
    }

    @DeleteMapping("/deletingTask/{id}")
    public void deleteTaskById(@PathVariable(value = "id") Long id) {
        taskServices.deleteTaskById(id);
    }

    
}
