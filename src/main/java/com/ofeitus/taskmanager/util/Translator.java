package com.ofeitus.taskmanager.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translator {
    private static final Locale locale = Locale.getDefault();

    private static final ResourceBundle rb = ResourceBundle.getBundle("lang/messages", locale);

    public static String getString(String key) {
        return rb.getString(key);
    }
}
