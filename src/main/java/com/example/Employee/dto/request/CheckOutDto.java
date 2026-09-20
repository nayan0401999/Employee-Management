package com.example.Employee.dto.request;

import java.time.LocalTime;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class CheckOutDto {

    @NotNull(message = "Exit time is required")
    private LocalTime exitTime;

}