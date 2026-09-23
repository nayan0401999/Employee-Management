package com.example.Employee.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.env.Environment;
import com.example.Employee.repository.AttendanceRepository;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.repository.LeaveRequestRepository;
import com.example.Employee.repository.ProjectRepository;
import com.example.Employee.service.AdminService;
import com.example.Employee.util.ResponseUtil;

@Service
public class AdminServiceImpl implements AdminService {

    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    private Environment env;

    public AdminServiceImpl(AttendanceRepository attendanceRepository,
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository) {
        this.attendanceRepository = attendanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteAllData() {
        attendanceRepository.deleteAll();
        leaveRequestRepository.deleteAll();
        employeeRepository.deleteAll();
        projectRepository.deleteAll();
        return ResponseUtil.build(HttpStatus.OK, "All data deleted successfully", null);
    }

    @Override
    public ResponseEntity<Object> getMethodName() {

        String applicationName = env.getProperty("spring.application.name");
        String port = env.getProperty("server.port");
        String message = env.getProperty("message");

        Map<String, String> value = Map.of(
                "applicationName", applicationName,
                "port", port,
                "message", message);

        return ResponseUtil.build(
                HttpStatus.OK,
                "Method executed successfully",
                value);
    }
}