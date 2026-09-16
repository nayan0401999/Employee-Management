package com.example.Employee.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Employee.dto.request.CreateProjectDto;
import com.example.Employee.dto.response.EmployeeBriefDto;
import com.example.Employee.dto.response.EmployeeResponseDto;
import com.example.Employee.dto.response.ProjectFullDetailsDto;
import com.example.Employee.dto.response.ProjectResponseDto;
import com.example.Employee.entity.Employee;
import com.example.Employee.entity.Project;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ResponseEntity<Object> createProject(CreateProjectDto dto) {
        Project saved = projectRepository.save(toEntity(dto));
        return buildResponse(HttpStatus.CREATED, "Project created successfully", toResponseDto(saved));
    }

    @Transactional
    public ResponseEntity<Object> assignEmployee(Long projectId, Long employeeId) {
        Project project = findProjectOrThrow(projectId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        if (!employee.getProjects().contains(project)) {
            employee.getProjects().add(project);
            project.getEmployees().add(employee);
        }
        return buildResponse(HttpStatus.OK, "Employee assigned to project successfully", toResponseDto(project));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getProjectById(Long id) {
        return buildResponse(HttpStatus.OK, "Project fetched successfully", toResponseDto(findProjectOrThrow(id)));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllProjects() {
        List<ProjectResponseDto> list = projectRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return buildResponse(HttpStatus.OK, "Projects fetched successfully", list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getProjectEmployeesBrief(Long projectId) {
        Project project = findProjectOrThrow(projectId);
        List<EmployeeBriefDto> brief = project.getEmployees() == null
                ? Collections.emptyList()
                : project.getEmployees().stream()
                        .map(e -> new EmployeeBriefDto(e.getName(), e.getDesignation()))
                        .collect(Collectors.toList());
        return buildResponse(HttpStatus.OK, "Project employees fetched successfully", brief);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getProjectFullDetails(Long projectId) {
        Project project = findProjectOrThrow(projectId);
        List<EmployeeResponseDto> employees = project.getEmployees() == null
                ? Collections.emptyList()
                : project.getEmployees().stream()
                        .map(e -> new EmployeeResponseDto(e.getId(), e.getName(), e.getDesignation(), e.getEmail()))
                        .collect(Collectors.toList());

        ProjectFullDetailsDto details = new ProjectFullDetailsDto(
                project.getId(), project.getName(), employees.size(), employees);
        return buildResponse(HttpStatus.OK, "Project full details fetched successfully", details);
    }

    @Transactional
    public ResponseEntity<Object> deleteAllProjects() {
        projectRepository.deleteAll();
        return buildResponse(HttpStatus.OK, "All projects deleted successfully", null);
    }

    private Project findProjectOrThrow(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    private Project toEntity(CreateProjectDto dto) {
        return new Project(dto.getName());
    }

    private ProjectResponseDto toResponseDto(Project project) {
        List<String> employeeNames = project.getEmployees() == null
                ? Collections.emptyList()
                : project.getEmployees().stream().map(Employee::getName).collect(Collectors.toList());
        return new ProjectResponseDto(project.getId(), project.getName(), employeeNames);
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", status.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(status).body(body);
    }
}