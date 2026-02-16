package com.employeeService.entity;

import com.employeeService.dto.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "email")
    private  String email;

    @Column(name = "age")
    private  Integer age;

    @Column(name = "date_of_birth")
    private LocalDate dateOfJoining;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_active")
    private EmployeeStatus isActive;
}
