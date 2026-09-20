package com.example.Employee.dto.response;

import java.time.LocalDate;
import com.example.Employee.enums.LeaveStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class LeaveRequestResponseDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;
    private String employeeName;
}