package org.eme.hrRestfulApp.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VacationCalculator {
    public static int calculateVacationDaysExcludingWeekends(LocalDate startDate, LocalDate endDate) {
        int vacationDays = 0;
        LocalDate date = startDate;

        while (date.isBefore(endDate) || date.isEqual(endDate)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.FRIDAY) {
                vacationDays++;
            }
            date = date.plus(1, ChronoUnit.DAYS);
        }

        return vacationDays;
    }
}
