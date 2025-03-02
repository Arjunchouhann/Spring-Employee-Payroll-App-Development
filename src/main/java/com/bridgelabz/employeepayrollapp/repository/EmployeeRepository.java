package com.bridgelabz.employeepayrollapp.repository;

import com.bridgelabz.employeepayrollapp.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    @Query(value = "select * from employee_payroll_table,employee_department where employee_id = id and department = :department" , nativeQuery = true)
    List<EmployeeEntity> findByDepartment(String department);
}