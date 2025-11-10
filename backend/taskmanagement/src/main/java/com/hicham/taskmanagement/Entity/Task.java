package com.hicham.taskmanagement.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task() {}

    public Task(String title, String description, TaskStatus taskStatus, User user) {
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
