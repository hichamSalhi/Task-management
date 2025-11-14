package com.hicham.taskmanagement.Controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.hicham.taskmanagement.Entity.ChatMessage;
import com.hicham.taskmanagement.Repository.ChatMessageRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*") 
@RestController
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages") // broadcast destination
    public ChatMessage sendMessage(ChatMessage message) {
        ChatMessage saved = chatMessageRepository.save(message);
        return saved;
    }

    @GetMapping("/chat/history")
    public List<ChatMessage> getHistory() {
        return chatMessageRepository.findAll();
    }
    
}
