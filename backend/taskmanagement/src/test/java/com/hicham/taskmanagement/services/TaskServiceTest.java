package com.hicham.taskmanagement.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Entity.TaskStatus;
import com.hicham.taskmanagement.Entity.User;
import com.hicham.taskmanagement.Repository.ITaskRepository;
import com.hicham.taskmanagement.Repository.IUserRepository;
import com.hicham.taskmanagement.Services.TaskServices;

public class TaskServiceTest {

    private ITaskRepository taskRepository;
    private IUserRepository userRepository;
    private TaskServices taskServices;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(ITaskRepository.class);
        userRepository = Mockito.mock(IUserRepository.class);
        taskServices = new TaskServices(taskRepository);
    }

    @Test
    void shouldSaveTaskSuccessfully() {
        User user = new User();
        userRepository.save(user);
        Task task = new Task("test task", "This is a task for testing purposes", TaskStatus.PENDING, user);
        taskServices.saveTask(task);
        verify(taskRepository,times(1)).save(task);
    }

    @Test
    void shouldDeleteTaskSById() {
        taskServices.deleteTaskById(1L);
        verify(taskRepository,times(1)).deleteById(1l);
    }

    @Test
    void shouldReturnListOfTasks() {
        User user = new User();
        userRepository.save(user);
        List<Task> tasks = List.of(
                                new Task("Task 1", "Description for the first task", TaskStatus.PENDING, user),
                                new Task("Task 2", "Description for the second task", TaskStatus.PENDING, user)
                            );
        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> resultOfTasks = taskServices.getTasks();

        assertEquals(2,resultOfTasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnTaskById() {
        User user = new User();
        userRepository.save(user);
        Task task = new Task("test task", "This is a task for testing purposes", TaskStatus.PENDING, user);
        when(taskRepository.findById(1l)).thenReturn(Optional.of(task));
        Task optionalTask = taskServices.getTaskById(1l);

        assertNotNull(optionalTask);
        assertEquals("test task", optionalTask.getTitle());
        verify(taskRepository,times(1)).findById(1l);
    }

    @Test
    void shouldReturnNullIfTaskNotFound() {
        when(taskRepository.findById(100L)).thenReturn(Optional.empty());
        Task resulTask = taskServices.getTaskById(99L);
        assertNull(resulTask);
        verify(taskRepository, times(1)).findById(99L);
    }

    @Test
    void shouldReturnTitleOfUrgentTasks() {
        User user = new User();
        userRepository.save(user);
        List<Task> tasks = List.of(
                                new Task("Task 1", "Description for the first task", TaskStatus.PENDING, user),
                                new Task("Task 2", "Description for the second task", TaskStatus.URGENT, user)
                            );
        when(taskRepository.findAll()).thenReturn(tasks);
        List<String> titlesAndUrgent = taskServices.getTitleOfUrgentTasks();
        titlesAndUrgent.forEach(task -> System.out.println(task));
        assertEquals("Task 2", titlesAndUrgent.getFirst());
    }
    
}