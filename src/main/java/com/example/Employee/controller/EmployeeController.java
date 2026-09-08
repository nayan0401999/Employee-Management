package com.example.Employee.controller;

import com.example.Employee.entity.Employee;
import com.example.Employee.model.EmployeeResponse;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

//    private final EmployeeService employeeService;
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    @GetMapping
//    public List<Employee> getEmployees() {
//        return employeeService.getEmployees();
//    }
//
//    @GetMapping({"/{id}"})
//    public EmployeeResponse getEmployeeById(@PathVariable String id) {
//        return employeeService.getEmployeeById(id);
//    }
//
//    @PostMapping
//    public EmployeeResponse addEmployee(@RequestBody  Employee employee) {
//        return employeeService.addEmployee(employee);
//    }
//
//    @DeleteMapping("/{id}")
//    public EmployeeResponse deleteEmployee(@PathVariable String id) {
//        return employeeService.deleteEmployee(id);
//    }
//
//    @PutMapping("/{id}")
//    public EmployeeResponse updateEmployee(@PathVariable String id , @RequestBody  Employee employee) {
//        return employeeService.updateEmployeeById(id , employee);
//    }


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
