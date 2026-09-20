package com.example.Employee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class EmployeeAverageAttendanceDto {
    private String name;
    private double averageHoursWorked;
}