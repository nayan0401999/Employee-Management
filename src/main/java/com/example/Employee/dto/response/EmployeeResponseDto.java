package com.example.Employee.dto.response;

public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String designation;
    private String email;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Long id, String name, String designation, String email) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.email = email;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}