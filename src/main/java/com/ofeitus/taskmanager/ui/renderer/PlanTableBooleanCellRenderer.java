package com.ofeitus.taskmanager.ui.renderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PlanTableBooleanCellRenderer extends DefaultTableCellRenderer {

    private final Color darkerColor = new Color(220, 220, 220);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
        if ((Boolean) value) {
            cellComponent.setBackground(darkerColor);
        } else {
            cellComponent.setBackground(Color.WHITE);
        }
        return cellComponent;
    }
}
