package com.employeeService.service.imp;

import com.employeeService.dto.EmployeeDto;
import com.employeeService.entity.EmployeeEntity;
import com.employeeService.repository.EmployeeRespository;
import com.employeeService.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        EmployeeEntity savedUser=employeeRespository.save(employeeEntity);
        return  modelMapper.map(savedUser,EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        EmployeeEntity employeeEntity =employeeRespository.findById(employeeId).get();
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<EmployeeEntity> employeesList=employeeRespository.findAll();
       return employeesList.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());

    }


}
