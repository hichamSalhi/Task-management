package com.hicham.taskmanagement.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Repository.ITaskRepository;

@Service
public class TaskServices {
    public ITaskRepository taskRepository;

    public TaskServices(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }
     
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
