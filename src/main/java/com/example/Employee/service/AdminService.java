package com.example.Employee.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Employee.repository.AttendanceRepository;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.repository.LeaveRequestRepository;
import com.example.Employee.repository.ProjectRepository;
import com.example.Employee.util.ResponseUtil;

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

        return ResponseUtil.build(HttpStatus.OK, "All data deleted successfully", null);
    }

    
}