package com.example.Employee.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Employee.entity.Project;

 public interface ProjectRepository extends JpaRepository<Project, Long> {

} 
