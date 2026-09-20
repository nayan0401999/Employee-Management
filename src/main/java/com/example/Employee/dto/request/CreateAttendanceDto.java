package com.example.Employee.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.Employee.enums.AttendanceStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
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

   
}