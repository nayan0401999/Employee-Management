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
public class ProjectResponseDto {
    private Long id;
    private String name;
    private List<String> employeeNames;

}