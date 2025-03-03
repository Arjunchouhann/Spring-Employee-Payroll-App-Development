package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollapp.model.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save the employee and return DTO
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        log.info("Adding employee: {}", employeeDTO);

        EmployeeEntity employee = convertToEntity(employeeDTO);
        EmployeeEntity savedEmployee = employeeRepository.save(employee);

        log.info("Employee saved with ID: {}", savedEmployee.getId());
        return convertToDTO(savedEmployee);
    }

    // Get all employees as DTOs (without streams)
    @Override
    public List<EmployeeDTO> getAllEmployee() {
        log.info("Fetching all employees...");
        List<EmployeeEntity> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        for (EmployeeEntity employee : employees) {
            employeeDTOs.add(convertToDTO(employee));
        }

        return employeeDTOs;
    }


    // Get employee by ID as DTO
    @Override
    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return Optional.of(convertToDTO(optionalEmployee.get()));
        } else {
            return Optional.empty();
        }
    }

    // Update employee and return DTO
    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with ID: {}", id);

        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            employee.setDepartment(employeeDTO.getDepartment());
            employee.setGender(employeeDTO.getGender());
            employee.setStartDate(employeeDTO.getStartDate());
            employee.setNote(employeeDTO.getNote());
            employee.setProfilePic(employeeDTO.getProfilePic());
            //     employee = convertToEntity(employeeDTO);
            EmployeeEntity updatedEmployee = employeeRepository.save(employee);
            log.info("Employee updated with ID: {}", id);
            return convertToDTO(updatedEmployee);
        } else {

            log.warn("Employee not found with ID: " + id);
            return null;
        }
    }

    // Delete employee
    @Override
    public ResponseEntity<String> deleteEmployee(Long id) {
        log.warn("Deleting employee with ID: {}", id);

        if (!employeeRepository.existsById(id)) {
            log.error("Employee not found with ID: {}", id);
            throw new EmployeeNotFoundException("No employee found with ID: " + id);
        }

        employeeRepository.deleteById(id);
        log.info("Employee deleted with ID: {}", id);
        return ResponseEntity.ok("Employee with ID " + id + " deleted successfully");
    }

    //Get the Employees Department-Wise
    @Override
    public List<EmployeeDTO> findByDepartment(String department) {
        List<EmployeeEntity>employees = employeeRepository.findByDepartment(department);
        List<EmployeeDTO>employeeDTOS = new ArrayList<>();
        for(EmployeeEntity employee : employees){
            employeeDTOS.add(convertToDTO(employee));
        }
        return employeeDTOS;
    }

    // Convert Employee to EmployeeDTO
    private EmployeeDTO convertToDTO(EmployeeEntity employee) {
        return new EmployeeDTO(employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getGender(),
                employee.getStartDate(),
                employee.getNote(),
                employee.getProfilePic(),
                employee.getDepartment());
    }

    // Convert EmployeeDTO to Employee
    private EmployeeEntity convertToEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setGender(employeeDTO.getGender());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setNote(employeeDTO.getNote());
        employee.setProfilePic(employeeDTO.getProfilePic());
        return employee;
    }
}