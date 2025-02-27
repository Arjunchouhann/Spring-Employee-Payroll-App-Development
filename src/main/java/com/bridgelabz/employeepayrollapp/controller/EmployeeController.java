package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    //Injecting employee repository class
    @Autowired
    EmployeeService employeeService;

    //Service To get all the employees
    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        if (logger.isInfoEnabled()) {
            logger.info("All employee endpoint called");
        }
        return employeeService.getAllEmployee();
    }

    //Service To get employee by id
    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<EmployeeEntity>> getEmployeeById(@PathVariable Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("By ID employee endpoint called with ID: {}", id);
        }
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee);
        } else {
            logger.warn("No employee found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }
    }


    //Service To create a new employee
    @PostMapping("/create")
    public EmployeeEntity addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        if (logger.isInfoEnabled()) {
            logger.info("Create employee endpoint called");
        }
        return employeeService.addEmployee(employeeDTO);
    }

    //Service To update the employee
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        if (logger.isInfoEnabled()) {
            logger.info("Update employee endpoint called with ID: {}", id);
        }
        EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            logger.warn("No employee found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //Service To delete the employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Delete employee endpoint called with ID: {}", id);
        }
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("No employee found with ID: {} to delete", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //
        }
    }
}