package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//UC2 & UC3 Introducing and adding Service layer
@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save the employee
    public EmployeeEntity addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return employeeRepository.save(employee);
    }

    // To get all the employees
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    // To get the employee by id
    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        // Try block
        try {
            return employeeRepository.findById(id);
        }
        // Catch block
        catch (Exception e) {
            logger.error("Error finding employee with ID {}", id, e);
            return Optional.empty();
        }
    }

    // Update the employee
    public EmployeeEntity updateEmployee(Long id, EmployeeDTO employeeDTO) {
        // Try block
        try {
            Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isPresent()) {
                logger.info("Updating employee with ID: {}", id);
                EmployeeEntity employee = optionalEmployee.get();
                employee.setName(employeeDTO.getName());
                employee.setDepartment(employeeDTO.getDepartment());
                employee.setSalary(employeeDTO.getSalary());
                return employeeRepository.save(employee);
            } else {
                logger.warn("No employee found with ID: {}", id);
                return null;
            }
        }
        // Catch block
        catch (Exception e) {
            logger.error("An unexpected error occurred while updating employee with ID: {}", id, e);
            return null;
        }
    }

    // Delete employee
    public boolean deleteEmployee(Long id) {
        // Try block
        try {
            employeeRepository.deleteById(id);
            logger.info("Deleted employee with ID: {}", id);
            return true;
        }
        // Catch block
        catch (Exception e) {
            logger.error("Error deleting employee with ID: {}", id, e);
            return false;
        }
    }
}