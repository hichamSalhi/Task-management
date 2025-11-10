package com.hicham.taskmanagement.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Entity.TaskStatus;
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

    public List<Task> getCompletedStatus() {
        return taskRepository.findAll()
                .stream()
                .filter(t -> t.getTaskStatus() == TaskStatus.COMPLETED)
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Task> getCompletedStatusById(Long userId) {
        return taskRepository.findAll()
            .stream()
            .filter(t -> t.getUser().getId().equals(userId) && t.getUser() != null)
            .filter(t -> t.getTaskStatus().equals(TaskStatus.COMPLETED))
            .sorted()
            .collect(Collectors.toList());
    }

    public List<String> getTitleOfUrgentTasks() {
        return taskRepository.findAll()
            .stream()
            .filter(task -> task.getTaskStatus().equals(TaskStatus.URGENT))
            .map(task -> task.getTitle())
            .sorted()
            .collect(Collectors.toList());
    }
}
