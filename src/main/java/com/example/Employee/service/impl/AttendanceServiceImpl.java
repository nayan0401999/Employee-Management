package com.example.Employee.service.impl;

import java.time.LocalDate;

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
import com.example.Employee.service.AttendanceService;
import com.example.Employee.util.ResponseUtil;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> checkIn(CreateAttendanceDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId()));

        attendanceRepository.findByEmployeeIdAndDate(dto.getEmployeeId(), dto.getDate())
                .ifPresent(a -> {
                    throw new InvalidAttendanceException(
                            "Attendance already recorded for employee " + dto.getEmployeeId() + " on " + dto.getDate());
                });

        Attendance attendance = toEntity(dto);
        attendance.setEmployee(employee);
        Attendance saved = attendanceRepository.save(attendance);
        return ResponseUtil.build(HttpStatus.CREATED, "Checked in successfully", toResponseDto(saved));
    }

    @Override
    @Transactional
    public ResponseEntity<Object> checkOut(Long attendanceId, CheckOutDto dto) {
        Attendance attendance = findAttendanceOrThrow(attendanceId);

        if (attendance.getExitTime() != null) {
            throw new InvalidAttendanceException("Attendance record " + attendanceId + " is already checked out");
        }
        if (dto.getExitTime().isBefore(attendance.getEntryTime())) {
            throw new InvalidAttendanceException("Exit time cannot be before entry time");
        }

        attendance.setExitTime(dto.getExitTime());
        return ResponseUtil.build(HttpStatus.OK, "Checked out successfully", toResponseDto(attendance));
    }

  
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getMonthlyAbsentCount(Long employeeId, int year, int month) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        long absentCount = attendanceRepository.countByEmployeeIdAndStatusAndDateBetween(
                employeeId, AttendanceStatus.ABSENT, start, end);
        return ResponseUtil.build(HttpStatus.OK, "Absent day count fetched successfully", absentCount);
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
}