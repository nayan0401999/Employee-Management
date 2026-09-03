package com.example.Employee.service;

import com.example.Employee.model.Employee;
import com.example.Employee.model.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private int id = 1;
    //read
    public List<Employee> getEmployees() {
        return employees;
    }

    public EmployeeResponse getEmployeeById(String id) {
        for (Employee employee : employees) {
            if(employee.getId().equals(id)) {
                return new EmployeeResponse(true , "Employee details Fetch successfully" , employee );
            }
        }
        return new EmployeeResponse(false , "Employee details Fetch failed" , null);
    }

    //create
    public EmployeeResponse addEmployee(Employee employee) {
        employee.setId(String.valueOf(id++));
        employees.add(employee);
        return new EmployeeResponse(true ,"Employee added successfully", employee);
    }

    //delete
    public EmployeeResponse deleteEmployee(String id) {
        for (Employee employee : employees) {
            if(employee.getId().equals(id)) {
                employees.remove(employee);
                return new EmployeeResponse(true , "Employee deleted successfully", employee);
            }
        }
        return new EmployeeResponse(false , "Employee Not Found", null);
    }

    //update
    public EmployeeResponse updateEmployeeById(String id , Employee employeeRequest) {
        for (Employee employee : employees) {
            if(employee.getId().equals(id)) {
                if(employeeRequest.getName() != null) {
                    employee.setName(employeeRequest.getName());
                }
                if(employeeRequest.getDesignation() != null) {
                    employee.setDesignation(employeeRequest.getDesignation());
                }

                return new EmployeeResponse(true , "Employee updated successfully", employee);
            }
        }
        return new EmployeeResponse(false , "Employee Not Found", null);
    }

}
