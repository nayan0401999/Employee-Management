package com.example.Employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Employee.dto.request.CheckOutDto;
import com.example.Employee.dto.request.CreateAttendanceDto;
import com.example.Employee.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<Object> checkIn(@Valid @RequestBody CreateAttendanceDto dto) {
        return attendanceService.checkIn(dto);
    }

    @PutMapping("/{id}/check-out")
    public ResponseEntity<Object> checkOut(@PathVariable Long id, @Valid @RequestBody CheckOutDto dto) {
        return attendanceService.checkOut(id, dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Object> getByEmployee(@PathVariable Long employeeId) {
        return attendanceService.getAttendanceByEmployee(employeeId);
    }

    @GetMapping("/employee/{employeeId}/absent-count")
    public ResponseEntity<Object> getMonthlyAbsentCount(
            @PathVariable Long employeeId,
            @RequestParam int year,
            @RequestParam int month) {
        return attendanceService.getMonthlyAbsentCount(employeeId, year, month);
    }
}