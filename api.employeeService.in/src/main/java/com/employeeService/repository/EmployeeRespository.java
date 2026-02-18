package com.employeeService.repository;

import com.employeeService.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRespository extends JpaRepository<EmployeeEntity,Long> {
}
