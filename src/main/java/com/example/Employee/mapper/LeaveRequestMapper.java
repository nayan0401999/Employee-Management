package com.example.Employee.mapper;

import com.example.Employee.dto.response.LeaveRequestResponseDto;
import com.example.Employee.entity.LeaveRequest;

public class LeaveRequestMapper {

    private LeaveRequestMapper() {
    }

    public static LeaveRequestResponseDto toResponseDto(LeaveRequest leaveRequest) {
        if (leaveRequest == null) {
            return null;
        }
        return new LeaveRequestResponseDto(
                leaveRequest.getId(),
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate(),
                leaveRequest.getStatus(),
                leaveRequest.getEmployee() != null ? leaveRequest.getEmployee().getName() : null);
    }
}