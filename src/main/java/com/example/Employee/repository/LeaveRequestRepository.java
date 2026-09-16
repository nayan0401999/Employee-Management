package com.example.Employee.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Employee.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    List<LeaveRequest> findByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long employeeId,
            java.time.LocalDate endDate, java.time.LocalDate startDate);

    boolean existsByEmployeeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Long employeeId, LocalDate date,
            LocalDate sameDate);
}
