package com.example.Employee.dto.request;

import java.time.LocalDate;

import com.example.Employee.enums.LeaveStatus;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public class CreateLeaveRequestDto {

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Employee id is required")
    private Long employeeId;

    private LeaveStatus status;

    public CreateLeaveRequestDto() {
    }

    @AssertTrue(message = "End date must not be before start date")
    private boolean isEndDateValid() {
        if (startDate == null || endDate == null) {
            return true; 
        }
        return !endDate.isBefore(startDate);
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }


}