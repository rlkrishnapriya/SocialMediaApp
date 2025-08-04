package com.example.liquibase_demo.dto;

import java.time.LocalDateTime;

public class ReactionDTO {

    private Integer id;
    private Integer userId;
    private Integer reactionTypeId;
    private Integer postId; 
    private String parentType; 
    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReactionTypeId() {
        return reactionTypeId;
    }

    public void setReactionTypeId(Integer reactionTypeId) {
        this.reactionTypeId = reactionTypeId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}