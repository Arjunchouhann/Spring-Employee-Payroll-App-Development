package com.bridgelabz.employeepayrollapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotEmpty(message = "Name is required cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$",
            message = "Name must start with an uppercase letter and does not having any special character")
    public String name;

    @Min(value = 500 , message = "Min Wage Should be more than 500")
    public double salary;

    @NotEmpty(message = "Gender is required and cannot be empty")
    @Pattern(regexp ="^male|female$",message = "Gender needs to be male or female")
    public String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @NotNull(message = "start date should not be empty")
    @PastOrPresent(message = "Start date must be a past or present date")
    public LocalDate startDate;

    @NotBlank(message = "Note cannot be empty")
    public String note;

    @NotBlank(message = "Profile Pic cannot be empty")
    public String profilePic;

    @NotNull(message = "department should not be empty")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<String> department;

}