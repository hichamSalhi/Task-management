package com.hicham.taskmanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hicham.taskmanagement.Entity.Task;
import com.hicham.taskmanagement.Entity.TaskStatus;
import com.hicham.taskmanagement.Entity.User;
import com.hicham.taskmanagement.Repository.ITaskRepository;
import com.hicham.taskmanagement.Repository.IUserRepository;

@SpringBootApplication
public class TaskmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ITaskRepository taskRepository, IUserRepository userRepository) {
		return args -> {
			// Add user
			User user1 = new User();
			userRepository.save(user1);
			// Add sample tasks
			taskRepository.save(new Task("Learn Spring Boot", "Study basics of Spring Boot and JPA", TaskStatus.PENDING,user1));
			taskRepository.save(new Task("Build API", "Create REST endpoints for tasks", TaskStatus.PENDING,user1));
			taskRepository.save(new Task("Connect PostgreSQL", "Use Docker for local PostgreSQL database", TaskStatus.COMPLETED,user1));

			System.out.println("Sample tasks inserted into database!");
		};
	}
}

