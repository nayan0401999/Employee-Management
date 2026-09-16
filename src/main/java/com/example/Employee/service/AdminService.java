package com.example.Employee.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Employee.repository.AttendanceRepository;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.repository.LeaveRequestRepository;
import com.example.Employee.repository.ProjectRepository;

@Service
public class AdminService {

    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    public AdminService(AttendanceRepository attendanceRepository,
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {
        this.attendanceRepository = attendanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public ResponseEntity<Object> deleteAllData() {

        attendanceRepository.deleteAll();
        leaveRequestRepository.deleteAll();

        employeeRepository.deleteAll();
        projectRepository.deleteAll();

        return buildResponse(HttpStatus.OK, "All data deleted successfully", null);
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", status.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(status).body(body);
    }
}