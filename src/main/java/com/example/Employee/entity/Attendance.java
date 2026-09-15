package com.example.Employee.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.Employee.enums.AttendanceStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date" , nullable = false)
    private LocalDate date;
    
    @Column(name = "entry_time" , nullable = false)
    private LocalTime entryTime;
    
    @Column(name = "exit_time" , nullable = false)
    private LocalTime exitTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false )
    private AttendanceStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id" , nullable = false) 
    private Employee employee;

    public Attendance() {
    }

    public Attendance(Long id, LocalDate date, AttendanceStatus status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
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

    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
