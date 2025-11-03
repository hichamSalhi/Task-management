package com.hicham.taskmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Repository.ITaskRepository;

@SpringBootApplication
public class TaskmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ITaskRepository taskRepository) {
		return args -> {
			// Add sample tasks
			taskRepository.save(new Task("Learn Spring Boot", "Study basics of Spring Boot and JPA"));
			taskRepository.save(new Task("Build API", "Create REST endpoints for tasks"));
			taskRepository.save(new Task("Connect PostgreSQL", "Use Docker for local PostgreSQL database"));

			System.out.println("Sample tasks inserted into database!");
		};
	}
}

