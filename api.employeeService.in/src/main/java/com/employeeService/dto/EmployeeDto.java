package com.employeeService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    private  String name;

    private  String email;

    private  Integer age;

    private LocalDate dateOfJoining;

    private  EmployeeStatus isActive;
}
