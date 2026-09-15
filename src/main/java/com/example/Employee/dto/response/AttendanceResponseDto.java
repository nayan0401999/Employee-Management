package com.example.Employee.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.Employee.enums.AttendanceStatus;

public class AttendanceResponseDto {
    private Long id;
    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private AttendanceStatus status;
    private String employeeName;

    public AttendanceResponseDto() {
    }

    public AttendanceResponseDto(Long id, LocalDate date, LocalTime entryTime, LocalTime exitTime,
            AttendanceStatus status, String employeeName) {
        this.id = id;
        this.date = date;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}