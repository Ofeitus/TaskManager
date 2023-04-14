package com.ofeitus.taskmanager.repository;

import com.ofeitus.taskmanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
