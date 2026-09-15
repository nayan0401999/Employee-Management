package com.example.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Employee.entity.Attendance;

public interface AttendanceRepository extends JpaRepository <Attendance, Long> {
}