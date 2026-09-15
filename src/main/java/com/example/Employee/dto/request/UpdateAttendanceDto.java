package com.example.Employee.dto.request;

import java.time.LocalTime;
import com.example.Employee.enums.AttendanceStatus;

public class UpdateAttendanceDto {
 
    private LocalTime entryTime;
    private LocalTime exitTime;
    private AttendanceStatus status;

    public UpdateAttendanceDto() {
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
}