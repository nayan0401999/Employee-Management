package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //create
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }
   //read
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee update(Long id , Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setDesignation(employee.getDesignation());
            existingEmployee.setName(employee.getName());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    //delete
    public void deleteById(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        }

    }
}
