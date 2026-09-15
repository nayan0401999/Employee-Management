package com.example.Employee.dto.response;

import java.util.List;

public class ProjectResponseDto {
    private Long id;
    private String name;
    private List<String> employeeNames;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Long id, String name, List<String> employeeNames) {
        this.id = id;
        this.name = name;
        this.employeeNames = employeeNames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmployeeNames() {
        return employeeNames;
    }

    public void setEmployeeNames(List<String> employeeNames) {
        this.employeeNames = employeeNames;
    }
}