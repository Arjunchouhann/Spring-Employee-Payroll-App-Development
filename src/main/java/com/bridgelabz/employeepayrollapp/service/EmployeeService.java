package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.model.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    //save the employee
    public EmployeeEntity addEmployee(EmployeeEntity employee){
        return employeeRepository.save(employee);
    }
    //To get all the employee
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }
    public Optional<EmployeeEntity>getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }
    //Update the employee
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity updatedEmployee){
        Optional<EmployeeEntity>optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            EmployeeEntity employee = optionalEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setSalary(updatedEmployee.getSalary());

            return employeeRepository.save(employee);

        }
        else{
            return null;
        }
    }

    //Delete employee
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

}