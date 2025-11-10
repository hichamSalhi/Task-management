package com.hicham.taskmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hicham.taskmanagement.Entity.User;

public interface IUserRepository extends JpaRepository<User, Long>{
    
}
