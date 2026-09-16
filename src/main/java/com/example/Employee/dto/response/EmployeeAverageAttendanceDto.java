package com.example.Employee.dto.response;

public class EmployeeAverageAttendanceDto {
    private String name;
    private double averageHoursWorked;

    public EmployeeAverageAttendanceDto() {
    }

    public EmployeeAverageAttendanceDto(String name, double averageHoursWorked) {
        this.name = name;
        this.averageHoursWorked = averageHoursWorked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageHoursWorked() {
        return averageHoursWorked;
    }

    public void setAverageHoursWorked(double averageHoursWorked) {
        this.averageHoursWorked = averageHoursWorked;
    }
}