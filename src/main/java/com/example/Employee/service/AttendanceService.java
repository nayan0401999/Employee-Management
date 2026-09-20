package com.example.Employee.service;

import org.springframework.http.ResponseEntity;
import com.example.Employee.dto.request.CheckOutDto;
import com.example.Employee.dto.request.CreateAttendanceDto;

public interface AttendanceService {
    ResponseEntity<Object> checkIn(CreateAttendanceDto dto);
    ResponseEntity<Object> checkOut(Long attendanceId, CheckOutDto dto);
    ResponseEntity<Object> getMonthlyAbsentCount(Long employeeId, int year, int month);
}