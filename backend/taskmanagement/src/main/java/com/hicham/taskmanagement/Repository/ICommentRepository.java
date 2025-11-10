package com.hicham.taskmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hicham.taskmanagement.Entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long>{
    
}
