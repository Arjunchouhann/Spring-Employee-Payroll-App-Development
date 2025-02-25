package com.bridgelabz.employeepayrollapp.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name;
    private double salary;
    private String department;
}