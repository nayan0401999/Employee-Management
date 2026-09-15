package com.example.Employee.mapper;

import com.example.Employee.dto.response.AttendanceResponseDto;
import com.example.Employee.entity.Attendance;

public class AttendanceMapper {

    private AttendanceMapper() {
    }

    public static AttendanceResponseDto toResponseDto(Attendance attendance) {
        if (attendance == null) {
            return null;
        }
        return new AttendanceResponseDto(
                attendance.getId(),
                attendance.getDate(),
                attendance.getEntryTime(),
                attendance.getExitTime(),
                attendance.getStatus(),
                attendance.getEmployee() != null ? attendance.getEmployee().getName() : null);
    }
}