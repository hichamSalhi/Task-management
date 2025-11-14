package com.hicham.taskmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hicham.taskmanagement.Entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
}
