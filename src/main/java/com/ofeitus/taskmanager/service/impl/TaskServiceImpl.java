package com.ofeitus.taskmanager.service.impl;

import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.model.Task;
import com.ofeitus.taskmanager.repository.TaskRepository;
import com.ofeitus.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        super();
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasksByEmployee(Employee employee) {
        return taskRepository.getAllByEmployeeId(employee.getId());
    }
}
