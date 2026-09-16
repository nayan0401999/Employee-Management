package com.example.Employee.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Employee.dto.request.CreateLeaveRequestDto;
import com.example.Employee.dto.response.LeaveRequestResponseDto;
import com.example.Employee.entity.Employee;
import com.example.Employee.entity.LeaveRequest;
import com.example.Employee.enums.LeaveStatus;
import com.example.Employee.exception.InvalidLeaveRequestException;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ResponseEntity<Object> createLeaveRequest(CreateLeaveRequestDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + dto.getEmployeeId()));

        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new InvalidLeaveRequestException("End date cannot be before start date");
        }

        boolean overlaps = !leaveRequestRepository
                .findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        dto.getEmployeeId(), dto.getEndDate(), dto.getStartDate())
                .isEmpty();
        if (overlaps) {
            throw new InvalidLeaveRequestException(
                    "Leave request overlaps with an existing leave request for this employee");
        }

        LeaveRequest saved = leaveRequestRepository.save(toEntity(dto));
        return buildResponse(HttpStatus.CREATED, "Leave request created successfully", toResponseDto(saved));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getLeaveRequestById(Long id) {
        return buildResponse(HttpStatus.OK, "Leave request fetched successfully", toResponseDto(findLeaveRequestOrThrow(id)));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getLeaveRequestsByEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        List<LeaveRequestResponseDto> list = leaveRequestRepository.findByEmployeeId(employeeId).stream()
                .map(leaveRequest -> this.toResponseDto(leaveRequest))
                .collect(Collectors.toList());
        return buildResponse(HttpStatus.OK, "Leave requests fetched successfully", list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> isEmployeeOnLeave(Long employeeId, LocalDate date) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        boolean onLeave = leaveRequestRepository
                .existsByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(employeeId, date, date);
        return buildResponse(HttpStatus.OK, "Leave status checked successfully", onLeave);
    }

    private LeaveRequest findLeaveRequestOrThrow(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));
    }

    private LeaveRequest toEntity(CreateLeaveRequestDto dto) {
        return new LeaveRequest(dto.getStartDate(), dto.getEndDate(), LeaveStatus.PENDING);
    }

    private LeaveRequestResponseDto toResponseDto(LeaveRequest leaveRequest) {
        return new LeaveRequestResponseDto(
                leaveRequest.getId(), leaveRequest.getStartDate(), leaveRequest.getEndDate(),
                leaveRequest.getStatus(), leaveRequest.getEmployee() != null ? leaveRequest.getEmployee().getName() : null);
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", status.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(status).body(body);
    }
}