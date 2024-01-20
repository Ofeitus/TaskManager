package com.ofeitus.taskmanager.util;

import java.time.LocalDateTime;

public class LocalDateTimeUtil {

    public static Boolean checkBetween(LocalDateTime dateToCheck, LocalDateTime startDate, LocalDateTime endDate) {
        return dateToCheck.isAfter(startDate) && !dateToCheck.isAfter(endDate);
    }
}
