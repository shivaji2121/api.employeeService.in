package com.employeeService.controllers;

import com.employeeService.dto.EmployeeDto;
import com.employeeService.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<EmployeeDto> employeeDto=employeeService.getEmployeeById(employeeId);
        return employeeDto.map(employeeDto1 -> ResponseEntity.ok(employeeDto1))
                .orElseThrow(()->new NoSuchElementException("Employee not found"));
    }

    @GetMapping(path = "/all")
    public  ResponseEntity<List<EmployeeDto>> getEmployees(){
        return ResponseEntity.ok(this.employeeService.getEmployees());
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@RequestBody EmployeeDto employeeDto,@PathVariable(required = true) Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDto,employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(required = true) Long employeeId){
        boolean gotDeleted=  employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployeePartially(@RequestBody Map<String,Object> updates,@PathVariable(required = true) Long employeeId){
       EmployeeDto employeeDto=employeeService.updateEmployeePartially(employeeId,updates);
       if (employeeDto==null) return ResponseEntity.notFound().build();
       return ResponseEntity.ok(employeeDto);
    }

}
