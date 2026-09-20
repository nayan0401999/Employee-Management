package com.example.Employee.dto.request;

import java.time.LocalDate;

import com.example.Employee.enums.LeaveStatus;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class CreateLeaveRequestDto {

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Employee id is required")
    private Long employeeId;

    private LeaveStatus status;


    @AssertTrue(message = "End date must not be before start date")
    private boolean isEndDateValid() {
        if (startDate == null || endDate == null) {
            return true; 
        }
        return !endDate.isBefore(startDate);
    }

}