package com.example.Employee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String designation;
    private String email;
}