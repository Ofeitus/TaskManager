package com.ofeitus.taskmanager.service;

import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByEmployee(Employee employee);
}
