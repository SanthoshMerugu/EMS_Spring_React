package com.backend.ems.mapper;

import com.backend.ems.dto.EmployeeDto;
import com.backend.ems.entity.Employee;

public class EmployeeMapper {
    public EmployeeMapper() {
    }

    public static EmployeeDto maptoEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail());
    }
}
