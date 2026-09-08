package com.example.Employee.model;

public class Employee {
    private String id;
    private String name;
    private String designation;


    public Employee(String id, String name, String designation) {
        this.id = id;
        this.name = name;
        this.designation = designation;
    }

    public String getName() {
        return name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
