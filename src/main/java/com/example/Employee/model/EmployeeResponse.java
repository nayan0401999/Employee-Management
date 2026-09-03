package com.example.Employee.model;

public class EmployeeResponse {
    public boolean success;
    public String message;
    public Employee employee;

    public EmployeeResponse( boolean success, String message, Employee employee) {
        this.success = success;
        this.message = message;
        this.employee = employee;
    }
}
