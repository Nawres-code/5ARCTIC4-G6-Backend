package com.Parking.GestionParking.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtils {

    public static LocalDateTime getStartDateOfPreviousMonth() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime firstDayOfThisMonth = today.withDayOfMonth(1);
        LocalDateTime firstDayOfPreviousMonth = firstDayOfThisMonth.minusMonths(1);
        return firstDayOfPreviousMonth;
    }

}
