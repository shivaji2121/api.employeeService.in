package com.employeeService.entity;

import com.employeeService.dto.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
