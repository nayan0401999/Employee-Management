package com.example.Employee.service;

import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<Object> deleteAllData();
    ResponseEntity<Object> getMethodName();
}