package com.ofeitus.taskmanager.manager;

import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.model.Task;
import com.ofeitus.taskmanager.service.TaskService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlanManager {
    private final TaskService taskService;

    @Getter
    @Setter
    private Month selectedMonth;

    @Getter
    private final List<Task> managedTasks;

    public PlanManager(TaskService taskService) {
        this.taskService = taskService;
        managedTasks = new ArrayList<>();
    }

    public void updateTasksByEmployee(Employee employee) {
        managedTasks.clear();
        managedTasks.addAll(taskService.getTasksByEmployee(employee));
    }
}
