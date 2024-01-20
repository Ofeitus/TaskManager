package com.ofeitus.taskmanager.ui.tablemodel;

import com.ofeitus.taskmanager.util.Translator;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;

public class TasksTableModel extends DefaultTableModel {

    public TasksTableModel() {

        super(new String[]{
                Translator.getString("completed"),
                Translator.getString("task"),
                Translator.getString("start-date"),
                Translator.getString("end-date"),
                Translator.getString("completion-date")
        }, 0);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Boolean.class;
            case 1 -> String.class;
            case 2, 3, 4 -> LocalDateTime.class;
            default -> super.getColumnClass(columnIndex);
        };
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
