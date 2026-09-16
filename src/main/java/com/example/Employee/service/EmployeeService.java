package com.example.Employee.service;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Employee.dto.request.CreateEmployeeDto;
import com.example.Employee.dto.response.EmployeeAverageAttendanceDto;
import com.example.Employee.dto.response.EmployeeResponseDto;
import com.example.Employee.entity.Attendance;
import com.example.Employee.entity.Employee;
import com.example.Employee.exception.DuplicateEmailException;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.repository.AttendanceRepository;
import com.example.Employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional
    public ResponseEntity<Object> createEmployee(CreateEmployeeDto dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Employee with email '" + dto.getEmail() + "' already exists");
        }
        Employee saved = employeeRepository.save(toEntity(dto));
        return buildResponse(HttpStatus.CREATED, "Employee created successfully", toResponseDto(saved));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getEmployeeById(Long id) {
        return buildResponse(HttpStatus.OK, "Employee fetched successfully", toResponseDto(findEmployeeOrThrow(id)));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeRepository.findAll().stream()
                .map(employee -> this.toResponseDto(employee))
                .collect(Collectors.toList());
        return buildResponse(HttpStatus.OK, "Employees fetched successfully", employees);
    }

    @Transactional
    public ResponseEntity<Object> deleteEmployee(Long id) {
        employeeRepository.delete(findEmployeeOrThrow(id));
        return buildResponse(HttpStatus.OK, "Employee deleted successfully", null);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getMultiProjectEmployeesAverageAttendance() {
        List<Employee> employees = employeeRepository.findEmployeesWithMultipleProjects();

        List<EmployeeAverageAttendanceDto> result = employees.stream()
                .map(e -> new EmployeeAverageAttendanceDto(e.getName(), calculateAverageHoursWorked(e.getId())))
                .collect(Collectors.toList());

        return buildResponse(HttpStatus.OK, "Multi-project employees fetched successfully", result);
    }

    private double calculateAverageHoursWorked(Long employeeId) {
        List<Attendance> records = attendanceRepository.findByEmployeeId(employeeId);

        double average = records.stream()
                .filter(a -> a.getEntryTime() != null && a.getExitTime() != null)
                .mapToDouble(a -> Duration.between(a.getEntryTime(), a.getExitTime()).toMinutes() / 60.0)
                .average()
                .orElse(0.0);

        return Math.round(average * 100.0) / 100.0; // round to 2 decimal places
    }

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private Employee toEntity(CreateEmployeeDto dto) {
        return new Employee(dto.getName(), dto.getDesignation(), dto.getEmail());
    }

    private EmployeeResponseDto toResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(), employee.getName(), employee.getDesignation(), employee.getEmail());
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", status.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(status).body(body);
    }
}