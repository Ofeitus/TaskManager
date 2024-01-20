package com.ofeitus.taskmanager.ui.table;

import com.ofeitus.taskmanager.manager.PlanManager;
import com.ofeitus.taskmanager.model.Task;
import com.ofeitus.taskmanager.ui.renderer.LocalDateTimeCellRenderer;
import com.ofeitus.taskmanager.ui.tablemodel.TasksTableModel;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;

@Component
public class TasksTable extends JTable implements Updatable {

    private final PlanManager planManager;

    public TasksTable(PlanManager planManager) {
        super(new TasksTableModel());
        this.planManager = planManager;
        setDefaultRenderer(LocalDateTime.class, new LocalDateTimeCellRenderer());
    }

    @Override
    public void update() {
        TasksTableModel tasksTableModel = ((TasksTableModel) getModel());

        tasksTableModel.setRowCount(0);

        for (Task task : planManager.getManagedTasks()) {
            tasksTableModel.addRow(new Object[]{
                    task.isCompleted(),
                    task.getName(),
                    task.getStartDate(),
                    task.getEndDate(),
                    task.getCompletionDate()
            });
        }
    }
}
