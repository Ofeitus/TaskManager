package com.ofeitus.taskmanager.ui.table;

import com.ofeitus.taskmanager.manager.PlanManager;
import com.ofeitus.taskmanager.model.Task;
import com.ofeitus.taskmanager.ui.renderer.PlanTableBooleanCellRenderer;
import com.ofeitus.taskmanager.ui.tablemodel.PlanTableModel;
import com.ofeitus.taskmanager.util.LocalDateTimeUtil;
import com.ofeitus.taskmanager.util.Translator;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Component
public class PlanTable extends JTable implements Updatable {

    private final PlanManager planManager;

    public PlanTable(PlanManager planManager) {
        super(new PlanTableModel());
        this.planManager = planManager;
        setDefaultRenderer(Boolean.class, new PlanTableBooleanCellRenderer());
        setFocusable(false);
        setRowSelectionAllowed(false);
    }

    @Override
    public void update() {
        PlanTableModel planTableModel = ((PlanTableModel) getModel());

        List<Task> tasks = planManager.getManagedTasks();

        Month selectedMonth = planManager.getSelectedMonth();
        int currentYear = Year.now().getValue();
        int lengthOfMonth = YearMonth.of(currentYear, selectedMonth).lengthOfMonth();

        Object[][] data = new Object[tasks.size()][lengthOfMonth + 1];
        for (int row = 0; row < tasks.size(); row++) {
            LocalDateTime currentCellDate = LocalDateTime.of(currentYear, selectedMonth, 1, 0, 0);
            for (int col = 0; col < lengthOfMonth + 1; col++) {
                if (col == 0) {
                    data[row][col] = tasks.get(row).getName();
                } else {
                    data[row][col] = LocalDateTimeUtil.checkBetween(currentCellDate, tasks.get(row).getStartDate(), tasks.get(row).getEndDate());
                    currentCellDate = currentCellDate.plusDays(1);
                }
            }
        }

        String[] columnNames = new String[lengthOfMonth + 1];
        columnNames[0] = Translator.getString("task");
        for (int col = 1; col < lengthOfMonth + 1; col++) {
            columnNames[col] = String.valueOf(col);
        }

        planTableModel.setDataVector(data, columnNames);
        getColumnModel().getColumn(0).setMinWidth(130);
    }
}
