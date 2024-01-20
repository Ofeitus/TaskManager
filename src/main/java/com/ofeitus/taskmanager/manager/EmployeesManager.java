package com.ofeitus.taskmanager.manager;

import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.service.EmployeeService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class EmployeesManager {
    @Setter
    private Employee selectedEmployee;

    private final List<Employee> managedEmployees;

    public EmployeesManager(EmployeeService employeeService) {
        managedEmployees = employeeService.getAllEmployees();
    }
}
