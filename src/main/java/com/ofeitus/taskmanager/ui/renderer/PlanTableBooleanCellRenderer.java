package com.ofeitus.taskmanager.ui.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PlanTableBooleanCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
        if ((Boolean) value) {
            cellComponent.setBackground(new Color(220, 220, 220));
        } else {
            cellComponent.setBackground(Color.WHITE);
        }
        return cellComponent;
    }
}
