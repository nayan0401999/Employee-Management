package com.example.Employee.dto.response;

public class EmployeeBriefDto {
    private String name;
    private String designation;

    public EmployeeBriefDto() {
    }

    public EmployeeBriefDto(String name, String designation) {
        this.name = name;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}