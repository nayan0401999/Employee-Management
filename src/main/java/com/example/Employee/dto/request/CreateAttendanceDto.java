package com.example.Employee.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.Employee.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

public class CreateAttendanceDto {

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Entry time is required")
    private LocalTime entryTime;

    private LocalTime exitTime;

    @NotNull(message = "Status is required")
    private AttendanceStatus status;

    @NotNull(message = "Employee id is required")
    private Long employeeId;

    public CreateAttendanceDto() {
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

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }
}