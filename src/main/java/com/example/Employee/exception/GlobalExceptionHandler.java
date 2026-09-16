package com.example.Employee.exception;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // 404 — entity not found
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
                        ResourceNotFoundException ex, HttpServletRequest request) {
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        // 409 — duplicate email
        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<ErrorResponseDto> handleDuplicateEmail(
                        DuplicateEmailException ex, HttpServletRequest request) {
                        
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // 400 — invalid leave request business rule (e.g. overlapping dates)
        @ExceptionHandler(InvalidLeaveRequestException.class)
        public ResponseEntity<ErrorResponseDto> handleInvalidLeaveRequest(
                        InvalidLeaveRequestException ex, HttpServletRequest request) {
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // 400 — invalid attendance operation (e.g. double check-in)
        @ExceptionHandler(InvalidAttendanceException.class)
        public ResponseEntity<ErrorResponseDto> handleInvalidAttendanceOperation(
                        InvalidAttendanceException ex, HttpServletRequest request) {
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // 400 — @Valid failures on @RequestBody DTOs (field-level messages)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDto> handleValidationErrors(
                        MethodArgumentNotValidException ex, HttpServletRequest request) {

                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.BAD_REQUEST.value(), "Validation failed", request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // 400 — malformed JSON body (missing braces, wrong type, empty body, etc.)
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponseDto> handleUnreadableBody(
                        HttpMessageNotReadableException ex, HttpServletRequest request) {
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.BAD_REQUEST.value(), "Malformed request body", request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // 400 — wrong type in path/query param, e.g. GET /employees/abc instead of
        // /employees/1
        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorResponseDto> handleTypeMismatch(
                        MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
                String message = String.format("Invalid value '%s' for parameter '%s'",
                                ex.getValue(), ex.getName());
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        // 409 — DB-level constraint violation (unique/foreign key) that slipped past
        // service checks
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ErrorResponseDto> handleDataIntegrityViolation(
                        DataIntegrityViolationException ex, HttpServletRequest request) {
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.CONFLICT.value(),
                                "Data integrity violation — check unique/foreign key constraints",
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // 500 — anything unanticipated; never leak the raw exception to the client
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDto> handleGenericException(
                        Exception ex, HttpServletRequest request) {
                ErrorResponseDto error = new ErrorResponseDto(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred",
                                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
}