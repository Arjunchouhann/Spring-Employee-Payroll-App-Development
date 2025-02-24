package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.model.EmployeeEntity;
import com.bridgelabz.employeepayrollapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    //To get all the employees
    @GetMapping
    public List<EmployeeEntity> getAllEmployees(){
        return employeeService.getAllEmployee();
    }

    //TO get employee with id
    @GetMapping("/get/{id}")
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/create")
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee){
        return employeeService.addEmployee(employee);
    }

    //Update the employee
    @PutMapping("/update/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity employee){
        return employeeService.updateEmployee(id,employee);
    }

    //Delete the employee
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }

}