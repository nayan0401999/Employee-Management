package com.example.Employee.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class CreateProjectDto {
    @NotNull(message = "Project name is required")
    private String name;

}