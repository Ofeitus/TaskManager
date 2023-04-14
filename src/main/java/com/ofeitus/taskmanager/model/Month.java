package com.ofeitus.taskmanager.model;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Month {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER;

    @Override
    public String toString() {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("lang/messages", locale);
        return rb.getString(this.name());
    }
}
