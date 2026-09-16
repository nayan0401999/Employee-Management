package com.example.Employee.dto.request;

import jakarta.validation.constraints.NotNull;

public class CreateProjectDto {
    @NotNull(message = "Project name is required")
    private String name;

    public CreateProjectDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}