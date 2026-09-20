package com.example.Employee.service;

import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import com.example.Employee.dto.request.CreateLeaveRequestDto;

public interface LeaveRequestService {
    ResponseEntity<Object> createLeaveRequest(CreateLeaveRequestDto dto);
    ResponseEntity<Object> getLeaveRequestById(Long id);
    ResponseEntity<Object> getLeaveRequestsByEmployee(Long employeeId);
    ResponseEntity<Object> isEmployeeOnLeave(Long employeeId, LocalDate date);
}