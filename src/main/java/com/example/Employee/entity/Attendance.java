package com.example.Employee.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "entry_time")
    private LocalTime entryTime;
    
    @Column(name = "exit_time")
    private LocalTime exitTime;
    
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "employee_id") 
    private Employee employeeId;

    public Attendance() {
    }

    public Attendance(Long id, LocalDate date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Employee getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }
}
