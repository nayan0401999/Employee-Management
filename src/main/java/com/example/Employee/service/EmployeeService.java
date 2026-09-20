package com.example.Employee.service;

import org.springframework.http.ResponseEntity;
import com.example.Employee.dto.request.CreateEmployeeDto;

public interface EmployeeService {
    ResponseEntity<Object> createEmployee(CreateEmployeeDto dto);
    ResponseEntity<Object> getEmployeeById(Long id);
    ResponseEntity<Object> getAllEmployees();
    ResponseEntity<Object> getMultiProjectEmployeesAverageAttendance();
}