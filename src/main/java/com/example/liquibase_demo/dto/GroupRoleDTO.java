package com.example.liquibase_demo.dto;

public class GroupRoleDTO {
    private int id;
    private String role;
    private String description;

    public GroupRoleDTO() {
    }

    public GroupRoleDTO(int id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}