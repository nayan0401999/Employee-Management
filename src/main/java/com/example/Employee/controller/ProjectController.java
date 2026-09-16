package com.example.Employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Employee.dto.request.CreateProjectDto;
import com.example.Employee.service.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateProjectDto dto) {
        return projectService.createProject(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return projectService.getAllProjects();
    }

    @PostMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<Object> assignEmployee(@PathVariable Long projectId, @PathVariable Long employeeId) {
        return projectService.assignEmployee(projectId, employeeId);
    }

    @GetMapping("/{projectId}/employees/brief")
    public ResponseEntity<Object> getProjectEmployeesBrief(@PathVariable Long projectId) {
        return projectService.getProjectEmployeesBrief(projectId);
    }

    @GetMapping("/{projectId}/full-details")
    public ResponseEntity<Object> getProjectFullDetails(@PathVariable Long projectId) {
        return projectService.getProjectFullDetails(projectId);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllProjects() {
        return projectService.deleteAllProjects();
    }
}