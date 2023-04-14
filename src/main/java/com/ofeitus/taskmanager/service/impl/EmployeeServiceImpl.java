package com.ofeitus.taskmanager.service.impl;

import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.repository.EmployeeRepository;
import com.ofeitus.taskmanager.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
