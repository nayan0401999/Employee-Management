package com.example.Employee.dto.response;

import java.util.List;

public class ProjectFullDetailsDto {
    private Long projectId;
    private String projectName;
    private int employeeCount;
    private List<EmployeeResponseDto> employees;

    public ProjectFullDetailsDto() {
    }

    public ProjectFullDetailsDto(Long projectId, String projectName, int employeeCount,
            List<EmployeeResponseDto> employees) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.employeeCount = employeeCount;
        this.employees = employees;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public List<EmployeeResponseDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponseDto> employees) {
        this.employees = employees;
    }
}
