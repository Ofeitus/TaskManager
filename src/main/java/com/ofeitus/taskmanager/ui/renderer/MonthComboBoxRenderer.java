package com.ofeitus.taskmanager.ui.renderer;

import com.ofeitus.taskmanager.util.Translator;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.time.Month;

public class MonthComboBoxRenderer extends BasicComboBoxRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return super.getListCellRendererComponent(list, Translator.getString(((Month) value).name()), index, isSelected, cellHasFocus);
    }
}
