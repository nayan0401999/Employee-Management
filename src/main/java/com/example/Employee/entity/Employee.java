package com.example.Employee.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "designation")
    private String designation;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leave_request> leaveRequests;

    @OneToMany(mappedBy = "employeeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendances;

    @ManyToMany
    @JoinTable(name = "employee_projects", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    public Employee() {
    }

    public Employee(String name, String designation, String email) {
        this.name = name;
        this.designation = designation;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Leave_request> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<Leave_request> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

}
