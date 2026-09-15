package com.example.Employee.controller;

import com.example.Employee.entity.Employee;
import com.example.Employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping({"/{id}"})
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
         employeeService.deleteById(id);
        return "Employee deleted successfully";
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id , @RequestBody  Employee employee) {
        return employeeService.update(id , employee);
    }
}
