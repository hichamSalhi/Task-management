package com.hicham.taskmanagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Entity.TaskStatus;
import com.hicham.taskmanagement.Entity.User;
import com.hicham.taskmanagement.Repository.ITaskRepository;
import com.hicham.taskmanagement.Repository.IUserRepository;

@SpringBootTest // Boots the full application for testing purposes
@AutoConfigureMockMvc //inject a fake HTTP client
public class TaskControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ITaskRepository iTaskRepository;

    @Autowired
    private IUserRepository userRepository;

    @BeforeEach
    void setUp() {
        iTaskRepository.deleteAll();
        User user = new User();
        userRepository.save(user);
        iTaskRepository.save(new Task("initial task", "description for initial task", TaskStatus.PENDING, user));
    }

    @Test
    void shouldReturnAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].title").value("initial task"));
    }

    @Test
    void shouldCreateNewTask() throws Exception {
        String newTaskJson = """
            {
              "title": "New Integration Task",
              "description": "Added via MockMvc test"
            }
            """;

        mockMvc.perform(post("/addingTask")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTaskJson))
                .andExpect(status().isOk());

        assertThat(iTaskRepository.findAll())
                .extracting(task -> task.getTitle())
                .contains("New Integration Task");
    }

    @Test
    void shouldReturnTaskById() throws Exception {
        User user = new User();
        userRepository.save(user);
        //Given
        Task insideTask = iTaskRepository.save(new Task("inside task", "description for inside task", TaskStatus.PENDING, user));
        //When + Then
        mockMvc.perform(get("/task/{id}", insideTask.getId()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("inside task"));
    }

    @Test
    void shouldReturnTaskByIdRequestParam() throws Exception {
        User user = new User();
        userRepository.save(user);
        //Given 
        Task insideTask = iTaskRepository.save(new Task("inside task", "Description for inside task", TaskStatus.PENDING, user));

        //When + then
        mockMvc.perform(get("/task").param("id", insideTask.getId().toString()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("inside task"));
    }
}
