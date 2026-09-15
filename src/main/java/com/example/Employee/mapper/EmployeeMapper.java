package com.example.Employee.mapper;

import com.example.Employee.dto.response.EmployeeResponseDto;
import com.example.Employee.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static EmployeeResponseDto toResponseDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getDesignation(),
                employee.getEmail());
    }
}