package com.example.Employee.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee.entity.Attendance;
import com.example.Employee.enums.AttendanceStatus;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeId(Long employeeId);
    Optional<Attendance> findByEmployeeIdAndDate(Long employeeId, java.time.LocalDate date);
    long countByEmployeeIdAndStatusAndDateBetween(Long employeeId, AttendanceStatus status, LocalDate start, LocalDate end);
}