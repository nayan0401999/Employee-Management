package com.example.Employee.dto.response;

import java.time.LocalDate;
import com.example.Employee.enums.LeaveStatus;

public class LeaveRequestResponseDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;
    private String employeeName;

    public LeaveRequestResponseDto() {
    }

    public LeaveRequestResponseDto(Long id, LocalDate startDate, LocalDate endDate, LeaveStatus status,
            String employeeName) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.employeeName = employeeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}