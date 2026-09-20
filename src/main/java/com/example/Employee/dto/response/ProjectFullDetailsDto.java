package com.example.Employee.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class ProjectFullDetailsDto {
    private Long projectId;
    private String projectName;
    private int employeeCount;
    private List<EmployeeResponseDto> employees;

}
