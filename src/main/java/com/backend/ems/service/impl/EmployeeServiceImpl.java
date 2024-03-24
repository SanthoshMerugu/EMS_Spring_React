package com.backend.ems.service.impl;

import com.backend.ems.dto.EmployeeDto;
import com.backend.ems.entity.Employee;
import com.backend.ems.exception.ResourceNotfoundException;
import com.backend.ems.mapper.EmployeeMapper;
import com.backend.ems.repository.EmployeeRepository;
import com.backend.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = (Employee)this.employeeRepository.save(employee);
        return EmployeeMapper.maptoEmployeeDto(savedEmployee);
    }

    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = (Employee)this.employeeRepository.findById(employeeId).orElseThrow(() -> {
            return new ResourceNotfoundException("Employee not exist:" + employeeId);
        });
        return EmployeeMapper.maptoEmployeeDto(employee);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        return (List)employees.stream().map(EmployeeMapper::maptoEmployeeDto).collect(Collectors.toList());
    }

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = (Employee)this.employeeRepository.findById(employeeId).orElseThrow(() -> {
            return new ResourceNotfoundException("Employee is not exist:" + employeeId);
        });
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee updatedEmployeeObj = (Employee)this.employeeRepository.save(employee);
        return EmployeeMapper.maptoEmployeeDto(updatedEmployeeObj);
    }

    public void deleteEmployee(Long employeeId) {
        Employee var10000 = (Employee)this.employeeRepository.findById(employeeId).orElseThrow(() -> {
            return new ResourceNotfoundException("Employee is not exist:" + employeeId);
        });
        this.employeeRepository.deleteById(employeeId);
    }

    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}