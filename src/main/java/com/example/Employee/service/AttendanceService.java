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

import com.example.Employee.dto.request.CheckOutDto;
import com.example.Employee.dto.request.CreateAttendanceDto;
import com.example.Employee.dto.response.AttendanceResponseDto;
import com.example.Employee.entity.Attendance;
import com.example.Employee.entity.Employee;
import com.example.Employee.enums.AttendanceStatus;
import com.example.Employee.exception.InvalidAttendanceException;
import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.repository.AttendanceRepository;
import com.example.Employee.repository.EmployeeRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ResponseEntity<Object> checkIn(CreateAttendanceDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + dto.getEmployeeId()));

        attendanceRepository.findByEmployeeIdAndDate(dto.getEmployeeId(), dto.getDate())
                .ifPresent(a -> {
                    throw new InvalidAttendanceException(
                            "Attendance already recorded for employee " + dto.getEmployeeId()
                                    + " on " + dto.getDate());
                });

        Attendance attendance = toEntity(dto);
        attendance.setEmployee(employee);
        Attendance saved = attendanceRepository.save(attendance);
        return buildResponse(HttpStatus.CREATED, "Checked in successfully", toResponseDto(saved));
    }

    @Transactional
    public ResponseEntity<Object> checkOut(Long attendanceId, CheckOutDto dto) {
        Attendance attendance = findAttendanceOrThrow(attendanceId);

        if (attendance.getExitTime() != null) {
            throw new InvalidAttendanceException(
                    "Attendance record " + attendanceId + " is already checked out");
        }
        if (dto.getExitTime().isBefore(attendance.getEntryTime())) {
            throw new InvalidAttendanceException("Exit time cannot be before entry time");
        }

        attendance.setExitTime(dto.getExitTime());
        return buildResponse(HttpStatus.OK, "Checked out successfully", toResponseDto(attendance));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAttendanceById(Long id) {
        return buildResponse(HttpStatus.OK, "Attendance fetched successfully", toResponseDto(findAttendanceOrThrow(id)));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAttendanceByEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        List<AttendanceResponseDto> list = attendanceRepository.findByEmployeeId(employeeId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
        return buildResponse(HttpStatus.OK, "Attendance records fetched successfully", list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Object> getMonthlyAbsentCount(Long employeeId, int year, int month) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        long absentCount = attendanceRepository.countByEmployeeIdAndStatusAndDateBetween(
                employeeId, AttendanceStatus.ABSENT, start, end);
        return buildResponse(HttpStatus.OK, "Absent day count fetched successfully", absentCount);
    }

    private Attendance findAttendanceOrThrow(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
    }

    private Attendance toEntity(CreateAttendanceDto dto) {
        Attendance attendance = new Attendance(dto.getDate(), dto.getStatus());
        attendance.setEntryTime(dto.getEntryTime());
        return attendance;
    }

    private AttendanceResponseDto toResponseDto(Attendance attendance) {
        return new AttendanceResponseDto(
                attendance.getId(), attendance.getDate(), attendance.getEntryTime(), attendance.getExitTime(),
                attendance.getStatus(), attendance.getEmployee() != null ? attendance.getEmployee().getName() : null);
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", status.value());
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(status).body(body);
    }
}