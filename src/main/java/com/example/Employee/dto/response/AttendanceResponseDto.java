package com.example.Employee.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.Employee.enums.AttendanceStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor  
@NoArgsConstructor 
public class AttendanceResponseDto {
    private Long id;
    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private AttendanceStatus status;
    private String employeeName;

}