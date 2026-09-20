package com.example.Employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Employee.dto.request.CreateEmployeeDto;
import com.example.Employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateEmployeeDto dto) {
        return employeeService.createEmployee(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/multi-project-attendance")
    public ResponseEntity<Object> getMultiProjectEmployeesAverageAttendance() {
        return employeeService.getMultiProjectEmployeesAverageAttendance();
    }
}