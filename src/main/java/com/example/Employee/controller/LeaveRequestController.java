package com.example.Employee.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Employee.dto.request.CreateLeaveRequestDto;
import com.example.Employee.service.LeaveRequestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateLeaveRequestDto dto) {
        return leaveRequestService.createLeaveRequest(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return leaveRequestService.getLeaveRequestById(id);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Object> getByEmployee(@PathVariable Long employeeId) {
        return leaveRequestService.getLeaveRequestsByEmployee(employeeId);
    }

    @GetMapping("/employee/{employeeId}/on-leave")
    public ResponseEntity<Object> isEmployeeOnLeave(
            @PathVariable Long employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return leaveRequestService.isEmployeeOnLeave(employeeId, date);
    }
}