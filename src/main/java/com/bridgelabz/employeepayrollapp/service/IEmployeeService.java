package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    // Get all employees as DTOs (without streams)
    List<EmployeeDTO> getAllEmployee();
    Optional<EmployeeDTO> getEmployeeById(Long id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    ResponseEntity<String> deleteEmployee(Long id);
}