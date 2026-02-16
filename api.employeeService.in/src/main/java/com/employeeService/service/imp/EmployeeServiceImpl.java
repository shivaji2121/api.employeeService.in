package com.employeeService.service.imp;

import com.employeeService.dto.EmployeeDto;
import com.employeeService.entity.EmployeeEntity;
import com.employeeService.repository.EmployeeRespository;
import com.employeeService.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public Optional<EmployeeDto> getEmployeeById(Long employeeId) {
        return employeeRespository.findById(employeeId)
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class));
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<EmployeeEntity> employeesList=employeeRespository.findAll();
       return employeesList.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDto,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity updatedEmployee=employeeRespository.save(employeeEntity);
        return modelMapper.map(updatedEmployee,EmployeeDto.class);
    }

    @Override
    public boolean deleteEmployeeById(Long employeeId) {
        boolean existById=isEmployeeExistById(employeeId);
        if(!existById) return  false;
        employeeRespository.deleteById(employeeId);
        return true;
    }


    @Override
    public EmployeeDto updateEmployeePartially(Long employeeId, Map<String, Object> updates) {
        boolean isExist=isEmployeeExistById(employeeId);
        if(!isExist) return  null;

        EmployeeEntity employeeEntity=employeeRespository.findById(employeeId).get();
        updates.forEach((field, value) -> {

            Field fieldToBeUpdated =
                    ReflectionUtils.findField(EmployeeEntity.class, field);

            if(fieldToBeUpdated!=null){

                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            }

        });
        System.out.println("employe patch update");
        employeeRespository.save(employeeEntity);
        return modelMapper.map(employeeEntity,EmployeeDto.class);
    }

    public boolean isEmployeeExistById(Long id){
        return  employeeRespository.existsById(id);
    }

}
