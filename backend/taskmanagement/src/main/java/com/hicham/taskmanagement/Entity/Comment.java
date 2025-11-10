package com.hicham.taskmanagement.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public Comment() {}

    public String getContent() {
        return content;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public Long getId() {
        return id;
    }
    public Task getTask() {
        return task;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public void setTask(Task task) {
        this.task = task;
    }
}
