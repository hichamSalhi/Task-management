package com.hicham.taskmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hicham.taskmanagement.Entity.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    
}
