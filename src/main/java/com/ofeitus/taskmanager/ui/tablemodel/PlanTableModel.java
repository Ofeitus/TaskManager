package com.ofeitus.taskmanager.ui.tablemodel;

import javax.swing.table.DefaultTableModel;

public class PlanTableModel extends DefaultTableModel {

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        } else {
            return Boolean.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
