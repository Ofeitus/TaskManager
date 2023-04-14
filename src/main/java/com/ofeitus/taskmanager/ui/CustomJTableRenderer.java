package com.ofeitus.taskmanager.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomJTableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            Component cellComponent = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
            if ((boolean)value) {
                cellComponent.setBackground(new Color(220, 220, 220));
            } else {
                cellComponent.setBackground(Color.WHITE);
            }
            return cellComponent;
        }

        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setBackground(Color.WHITE);
        return cellComponent;
    }
}
