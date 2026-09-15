package com.example.Employee.exception;

public class InvalidAttendanceException extends RuntimeException {
    public InvalidAttendanceException(String message) {
        super(message);
    }
}