package com.employeeService.controllers;

import com.employeeService.dto.EmployeeDto;
import com.employeeService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private  EmployeeService employeeService;



    @PostMapping(path = "/save")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedUser=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }

    @GetMapping(path = "/{employeeId}")
    public  ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(required = true) Long employeeId){
        return ResponseEntity.ok(this.employeeService.getEmployeeById(employeeId));
    }

    @GetMapping(path = "/all")
    public  ResponseEntity<List<EmployeeDto>> getEmployees(){
        return ResponseEntity.ok(this.employeeService.getEmployees());
    }

}
