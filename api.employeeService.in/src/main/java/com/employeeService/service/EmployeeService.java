package com.employeeService.service;

import com.employeeService.dto.EmployeeDto;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface EmployeeService {
     EmployeeDto createEmployee(EmployeeDto employeeDto);

    Optional<EmployeeDto> getEmployeeById(Long employeeId);

    List<EmployeeDto> getEmployees();

     EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId);

    boolean deleteEmployeeById(Long employeeId);

    EmployeeDto updateEmployeePartially(Long employeeId, Map<String, Object> updates);
}
