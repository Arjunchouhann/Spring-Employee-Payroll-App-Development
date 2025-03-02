package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    //Post Add employee
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    // Get all employees as DTOs (without streams)
    List<EmployeeDTO> getAllEmployee();
    //Get Employee By ID
    Optional<EmployeeDTO> getEmployeeById(Long id);
    //Update Employee By ID
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    //Delete Employee By ID
    ResponseEntity<String> deleteEmployee(Long id);
    //Finding employee by department
    List<EmployeeDTO> findByDepartment(String sales);
}