package com.example.Employee.service;

import org.springframework.http.ResponseEntity;
import com.example.Employee.dto.request.CreateProjectDto;

public interface ProjectService {
    ResponseEntity<Object> createProject(CreateProjectDto dto);
    ResponseEntity<Object> assignEmployee(Long projectId, Long employeeId);
    ResponseEntity<Object> getProjectById(Long id);
    ResponseEntity<Object> getAllProjects();
    ResponseEntity<Object> getProjectEmployeesBrief(Long projectId);
    ResponseEntity<Object> getProjectFullDetails(Long projectId);
}