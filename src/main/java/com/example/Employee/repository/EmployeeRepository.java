package com.example.Employee.repository;

import com.example.Employee.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    Employee findByEmail(String email);

    @Query("SELECT DISTINCT e FROM Employee e WHERE SIZE(e.projects) > 1")
    List<Employee> findEmployeesWithMultipleProjects();


}
