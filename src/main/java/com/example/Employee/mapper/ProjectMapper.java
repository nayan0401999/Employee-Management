package com.example.Employee.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Employee.dto.response.ProjectResponseDto;
import com.example.Employee.entity.Project;

public class ProjectMapper {

    private ProjectMapper() {
    }

    public static ProjectResponseDto toResponseDto(Project project) {
        if (project == null) {
            return null;
        }
        List<String> employeeNames = project.getEmployees() == null
                ? Collections.emptyList()
                : project.getEmployees().stream()
                        .map(e -> e.getName())
                        .collect(Collectors.toList());

        return new ProjectResponseDto(project.getId(), project.getName(), employeeNames);
    }
}