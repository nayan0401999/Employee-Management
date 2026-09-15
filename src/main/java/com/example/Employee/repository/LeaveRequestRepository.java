package com.example.Employee.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Employee.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    
}
