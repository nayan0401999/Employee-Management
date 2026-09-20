package com.example.Employee.service.impl;

import java.time.Duration;
import java.util.List;
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
import com.example.Employee.service.EmployeeService;
import com.example.Employee.util.ResponseUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> createEmployee(CreateEmployeeDto dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Employee with email '" + dto.getEmail() + "' already exists");
        }
        Employee saved = employeeRepository.save(toEntity(dto));
        return ResponseUtil.build(HttpStatus.CREATED, "Employee created successfully", toResponseDto(saved));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getEmployeeById(Long id) {
        return ResponseUtil.build(HttpStatus.OK, "Employee fetched successfully", toResponseDto(findEmployeeOrThrow(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return ResponseUtil.build(HttpStatus.OK, "Employees fetched successfully", employees);
    }


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getMultiProjectEmployeesAverageAttendance() {
        List<Employee> employees = employeeRepository.findEmployeesWithMultipleProjects();
        List<EmployeeAverageAttendanceDto> result = employees.stream()
                .map(e -> new EmployeeAverageAttendanceDto(e.getName(), calculateAverageHoursWorked(e.getId())))
                .collect(Collectors.toList());
        return ResponseUtil.build(HttpStatus.OK, "Multi-project employees fetched successfully", result);
    }

    private double calculateAverageHoursWorked(Long employeeId) {
        List<Attendance> records = attendanceRepository.findByEmployeeId(employeeId);
        double average = records.stream()
                .filter(a -> a.getEntryTime() != null && a.getExitTime() != null)
                .mapToDouble(a -> Duration.between(a.getEntryTime(), a.getExitTime()).toMinutes() / 60.0)
                .average()
                .orElse(0.0);
        return Math.round(average * 100.0) / 100.0;
    }

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private Employee toEntity(CreateEmployeeDto dto) {
        return new Employee(dto.getName(), dto.getDesignation(), dto.getEmail());
    }

    private EmployeeResponseDto toResponseDto(Employee employee) {
        return new EmployeeResponseDto(employee.getId(), employee.getName(), employee.getDesignation(), employee.getEmail());
    }
}