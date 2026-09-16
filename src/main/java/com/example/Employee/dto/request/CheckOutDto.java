package com.example.Employee.dto.request;

import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;

public class CheckOutDto {

    @NotNull(message = "Exit time is required")
    private LocalTime exitTime;

    public CheckOutDto() {
    }

    public LocalTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalTime exitTime) {
        this.exitTime = exitTime;
    }
}